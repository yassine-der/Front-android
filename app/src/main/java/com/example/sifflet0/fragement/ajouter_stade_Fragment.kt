package com.example.sifflet0.fragement

import android.content.ClipDescription
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.service.controls.ControlsProviderService
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.example.sifflet0.R
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.models.Stade
import com.example.sifflet0.models.User
import com.example.sifflet0.register
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ajouter_stade_Fragment : Fragment() {


    private  val args :ajouter_stade_FragmentArgs by navArgs()

    private var selectedImageUri0: Uri? = null
    var imagePicker0: ImageView?=null
    private lateinit var fab0: FloatingActionButton



    lateinit var emailEditText: TextInputEditText
    lateinit var emailLayoutLogin: TextInputLayout

    lateinit var passwordEditText: TextInputEditText
    lateinit var passwordLayoutLogin: TextInputLayout

    lateinit var addStadeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_ajouter_stade_, container, false)

        emailEditText = rootView.findViewById(R.id.nomAddStadeText)
        emailLayoutLogin = rootView.findViewById(R.id.nomAddStade)

        passwordEditText = rootView.findViewById(R.id.descriptonAddStadeText)
        passwordLayoutLogin = rootView.findViewById(R.id.descriptonAddStade)

        addStadeButton = rootView.findViewById(R.id.button4)

        fab0 = rootView.findViewById(R.id.floatingActionButton1)

        fab0.setOnClickListener{
            pickImageFromGallery()
        }
        addStadeButton.setOnClickListener {

            val nom = emailEditText.text.toString().trim()
            val description = passwordEditText.text.toString().trim()
            val lat = args.lat.toString()
            val long = args.long.toString()
            //val parcelFileDescriptor = contentResolver.openFileDescriptor(selectedImageUri0!!, "r", null) ?: return@setOnClickListener
             var resolver = requireActivity().contentResolver.openFileDescriptor(selectedImageUri0!!, "r", null) ?: return@setOnClickListener


            doAddStade(
                nom,description,lat,long
            )
            //print(parcelFileDescriptor);
        }

        // Inflate the layout for this fragment
        return rootView
    }

    private fun doAddStade(nom: String, description: String, lat: String, long: String){
        if (validate()) {

            if (selectedImageUri0 == null) {
                println("image null")

                return
            }


            //val stream = contentResolver.openInputStream(selectedImageUri0!!)
            val stream = requireActivity().contentResolver.openInputStream(selectedImageUri0!!)
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

            val apiInterface = RetrofiteInstance.api(context)
            val data: LinkedHashMap<String, RequestBody> = LinkedHashMap()

            data["nom"] = nom.toRequestBody(MultipartBody.FORM)
            data["discription"] = description.toRequestBody(MultipartBody.FORM)
            data["lat"] = lat.toRequestBody(MultipartBody.FORM)
            data["lon"] = long.toRequestBody(MultipartBody.FORM)
            if (image?.body != null) {

                println("++++++++++++++++++++++++++++++++++++" + image)
                apiInterface.addStade(data, image).enqueue(object :
                    Callback<Stade> {
                    override fun onResponse(
                        call: Call<Stade>,
                        response: Response<Stade>
                    ) {
                        if (response.isSuccessful) {
                            Log.i("onResponse goooood", response.body().toString())
                            //showAlertDialog()
                            Toast.makeText(context, "welcome ", Toast.LENGTH_SHORT).show()

                        } else {
                            Log.i("OnResponse not good", response.body().toString())
                        }
                    }

                    override fun onFailure(call: Call<Stade>, t: Throwable) {
                        //progress_bar.progress = 0
                        Toast.makeText(context, "Connexion error!", Toast.LENGTH_SHORT).show()

                    }

                })
            }
            else{
                Toast.makeText(context, "Choisir une image!", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, register.IMAGE_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == register.IMAGE_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            selectedImageUri0 = data?.data

            imagePicker0?.setImageURI(selectedImageUri0)
            fab0.hide()

        }
    }    private fun validate(): Boolean {
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


}