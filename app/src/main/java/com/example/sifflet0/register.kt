package com.example.sifflet0

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.api.UploadRequestBody
import com.example.sifflet0.models.User
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
//import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
            //doRegister()
        }
        registerButton.setOnClickListener {
            val nom = nomEditText.text.toString().trim()
            val prenom = prenomEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val isProprietaireDestade = "false"
            //val pro = editTextNum.text.toString().trim()



            if(nom.isEmpty()){
                nomEditText.error = "Email required"
                nomEditText.requestFocus()
                return@setOnClickListener
            }
            if(prenom.isEmpty()){
                prenomEditText.error = "Password required"
                prenomEditText.requestFocus()
                return@setOnClickListener
            }
            if(nom.isEmpty()){
                emailEditText.error = "Name required"
                emailEditText.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                passwordEditText.error = "Last name required"
                passwordEditText.requestFocus()
                return@setOnClickListener
            }

            val parcelFileDescriptor = contentResolver.openFileDescriptor(selectedImageUri!!, "r", null) ?: return@setOnClickListener
            login(
                nom,prenom,
                email,
                password,


            )
            print(parcelFileDescriptor);
        }
    }

    private fun login(nom: String, prenom: String, email: String, motdepasse: String){

        if(selectedImageUri == null){
            println("image null")

            return
        }


        val stream = contentResolver.openInputStream(selectedImageUri!!)
        println("-------------------------------------"+stream)
        val request =
            stream?.let { RequestBody.create("image/jpg".toMediaTypeOrNull(), it.readBytes()) } // read all bytes using kotlin extension
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
        //data["isProprietaireDestade"] = RequestBody.create(MultipartBody.FORM, isProprietaireDestade)

        if (image != null) {
            println("++++++++++++++++++++++++++++++++++++"+image)
            apiInterface.register(data,image).enqueue(object:
                Callback<User> {
                override fun onResponse(
                    call: Call<User>,
                    response: Response<User>
                ) {
                    if(response.isSuccessful){
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



/*
        private fun validate(): Boolean {
            emailLayoutLogin.error = null
            passwordLayoutLogin.error = null

            if (emailEditText.text!!.isEmpty()) {
                emailLayoutLogin.error = getString(R.string.mustNotBeEmpty)
                return false
            }

            if (passwordEditText.text!!.isEmpty()) {
                passwordLayoutLogin.error = getString(R.string.mustNotBeEmpty)
                return false
            }

            return true
        }
*/
}