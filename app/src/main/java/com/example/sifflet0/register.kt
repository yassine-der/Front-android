package com.example.sifflet0

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.models.User
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//import com.github.dhaval2404.imagepicker.ImagePicker
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


class register : AppCompatActivity() {
    lateinit var nomEditText: TextInputEditText
    lateinit var nomLayoutRegister: TextInputLayout

    lateinit var prenomEditText: TextInputEditText
    lateinit var prenomLayoutRegister: TextInputLayout

    lateinit var emailEditText: TextInputEditText
    lateinit var emailLayoutRegister: TextInputLayout

    lateinit var confirmePasswordEditText: TextInputEditText
    lateinit var confirmePasswordLayoutRegister: TextInputLayout

    lateinit var passwordEditText: TextInputEditText
    lateinit var passwordLayoutRegiser: TextInputLayout

    lateinit var registerButton: Button
    //lateinit var registerImageButton: Button
    private var selectedImageUri: Uri? = null
    var imagePicker: ImageView?=null
    private lateinit var fab: FloatingActionButton

    var radioGroup: RadioGroup? = null
    lateinit var radioButtonP: RadioButton
    lateinit var radioButtonS: RadioButton
    lateinit var radioButton: RadioButton


    lateinit var imageRegister: ImageView
    companion object {
        val IMAGE_REQUEST_CODE = 1_000;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Assigning id of RadioGroup
        radioGroup = findViewById(R.id.radioGroup)
        radioButtonP = findViewById(R.id.radioButtonp)
        radioButtonS = findViewById(R.id.radioButton7)

        nomEditText = findViewById(R.id.nomRegisterText)
        nomLayoutRegister = findViewById(R.id.nomRegister)

        prenomEditText = findViewById(R.id.prenomRegisterText)
        prenomLayoutRegister = findViewById(R.id.prenomRegister)

        emailEditText = findViewById(R.id.emailRegisterText)
        emailLayoutRegister = findViewById(R.id.emailRegister)

        confirmePasswordEditText = findViewById(R.id.confirmePasswordRegisterText)
        confirmePasswordLayoutRegister = findViewById(R.id.confirmePasswordRegister)

        passwordEditText = findViewById(R.id.passwordRegisterText)
        passwordLayoutRegiser = findViewById(R.id.passwordRegister)



        fab = findViewById(R.id.floatingActionButton)
        imagePicker = findViewById(R.id.imageRegister)
        registerButton = findViewById(R.id.Registerbutton)

        fab.setOnClickListener{
            pickImageFromGallery()
        }
        registerButton.setOnClickListener {
            val  selectedOption:Int = radioGroup!!.checkedRadioButtonId;

            val nom = nomEditText.text.toString().trim()
            val prenom = prenomEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmePasswordEditText.text.toString().trim()
            radioButton = findViewById(selectedOption)
            //Toast.makeText(this,radioButtonP.text,Toast.LENGTH_LONG).show()
            //radioButtonP.setChecked(true);
            //radioButtonS.setChecked(true);

            var isProprietaireDestade :String
            if(radioButton.text.toString() == "Proprietaire de stade"){
                 isProprietaireDestade = "ProprietaireDestade";
            }else{
                isProprietaireDestade = "SimpleUser"
            }



/*
            if((radioButtonS.isChecked == false) && (radioButtonS.isChecked == false)){
                Toast.makeText(this@register, "choisir votre role ", Toast.LENGTH_SHORT).show()

            }

 */
            val parcelFileDescriptor = contentResolver.openFileDescriptor(selectedImageUri!!, "r", null) ?: return@setOnClickListener
            doRegister(
                nom,prenom,
                email,
                password,
                isProprietaireDestade


            )
            print(parcelFileDescriptor);
        }
    }

    private fun doRegister(nom: String, prenom: String, email: String, motdepasse: String,isProprietaireDestade : String){
        if (validate()) {

            if (selectedImageUri == null) {
                println("image null")

                return
            }


            val stream = contentResolver.openInputStream(selectedImageUri!!)
            println("-------------------------------------" + stream)
            val request =
                stream?.let {
                    RequestBody.create(
                        "image/jpg".toMediaTypeOrNull(),
                        it.readBytes()
                    )
                } // read all bytes using kotlin extension
            val image = request?.let {
                MultipartBody.Part.createFormData(
                    "image",
                    "image.jpg",
                    it
                )
            }


            Log.d("MyActivity", "on finish upload file")

            val apiInterface = RetrofiteInstance.api(this)
            val data: LinkedHashMap<String, RequestBody> = LinkedHashMap()

            data["nom"] = nom.toRequestBody(MultipartBody.FORM)
            data["prenom"] = prenom.toRequestBody(MultipartBody.FORM)
            data["email"] = email.toRequestBody(MultipartBody.FORM)
            data["motdepasse"] = motdepasse.toRequestBody(MultipartBody.FORM)
            data["isProprietaireDestade"] = isProprietaireDestade.toRequestBody(MultipartBody.FORM)
            if (image?.body != null) {

                println("++++++++++++++++++++++++++++++++++++" + image)
                Log.d(TAG, isProprietaireDestade.toString())
                apiInterface.register(data, image).enqueue(object :
                    Callback<User> {
                    override fun onResponse(
                        call: Call<User>,
                        response: Response<User>
                    ) {
                        if (response.isSuccessful) {
                            Log.i("onResponse goooood", response.body().toString())
                            //showAlertDialog()
                            Toast.makeText(this@register, "welcome ", Toast.LENGTH_SHORT).show()

                        } else {
                            Log.i("OnResponse not good", response.body().toString())
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        //progress_bar.progress = 0
                        Toast.makeText(this@register, "Connexion error!", Toast.LENGTH_SHORT).show()

                        println("noooooooooooooooooo")
                    }

                })
            }
            else{
                Toast.makeText(this@register, "Choisir une image!", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            selectedImageUri = data?.data

            imagePicker?.setImageURI(selectedImageUri)
            fab.hide()

        }
    }
/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == ImagePicker.REQUEST_CODE){
            selectedImageUri = data?.data
            imagePicker?.setImageURI(selectedImageUri)

        }
    }

    private fun showAlertDialog(){
        MaterialAlertDialogBuilder(this)
            .setTitle("Alert")
            .setMessage("Thank you for choosing showapp.tn! \n We have sent you an email to confirm your account")
            .setPositiveButton("Ok") {dialog, which ->
                showSnackbar("welcome")
            }
            .show()
    }
    private fun showSnackbar(msg: String) {
        Snackbar.make(layout_root, msg, Snackbar.LENGTH_SHORT).show()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }


*/

    private fun validate(): Boolean {
        prenomLayoutRegister.error = null
        nomLayoutRegister.error = null
        emailLayoutRegister.error = null
        confirmePasswordLayoutRegister.error = null
        passwordLayoutRegiser.error = null


        if (nomEditText.text!!.isEmpty()) {
            nomLayoutRegister.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        if (prenomEditText.text!!.isEmpty()) {
            prenomLayoutRegister.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        if (emailEditText.text!!.isEmpty()) {
            emailLayoutRegister.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        if (confirmePasswordEditText.text!!.isEmpty()) {
            confirmePasswordLayoutRegister.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        if (passwordEditText.text!!.isEmpty()) {
            passwordLayoutRegiser.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        if (passwordEditText.text!!.isEmpty()) {
            passwordLayoutRegiser.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        /*
        val checkedRadioButton = group?.findViewById(group.checkedRadioButtonId) as? RadioButton
        checkedRadioButton?.let {

            if (checkedRadioButton.isChecked)
                Toast.makeText(applicationContext, "RadioGroup: ${group?.contentDescription} RadioButton: ${checkedRadioButton?.text}", Toast.LENGTH_LONG).show()
        }
*/

        return true
    }





}