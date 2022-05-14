package com.example.sifflet0

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.fragement.addEquipeFragmentDirections
import com.example.sifflet0.models.Equipe
import com.example.sifflet0.models.Joueur
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

class UserCreateJoueurFragment : Fragment() {

    private var selectedImageUri0: Uri? = null
    var imagePicker0: ImageView?=null
    private lateinit var fab2: FloatingActionButton



    lateinit var emailEditText: TextInputEditText
    lateinit var emailLayoutLogin: TextInputLayout

    lateinit var prenomEditText: TextInputEditText
    lateinit var prenomLayoutLogin: TextInputLayout

    lateinit var ageEditText: TextInputEditText
    lateinit var ageLayoutLogin: TextInputLayout

    lateinit var longueurEditText: TextInputEditText
    lateinit var longueurLayoutLogin: TextInputLayout

    lateinit var tailleEditText: TextInputEditText
    lateinit var tailleLayoutLogin: TextInputLayout

    lateinit var numEditText: TextInputEditText
    lateinit var numLayoutLogin: TextInputLayout

    lateinit var descriptionEditText: TextInputEditText
    lateinit var descriptionLayoutLogin: TextInputLayout

    lateinit var addStadeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_user_create_joueur, container, false)
        imagePicker0 = rootView.findViewById(R.id.circleImageViewLiguej)

        emailEditText = rootView.findViewById(R.id.nomAddLigueTextj)
        emailLayoutLogin = rootView.findViewById(R.id.nomAddLiguej)

        prenomEditText = rootView.findViewById(R.id.nomAddLigueTextj)
        prenomLayoutLogin = rootView.findViewById(R.id.nomAddLiguej)

        ageEditText = rootView.findViewById(R.id.prenomJoueurTextj)
        ageLayoutLogin = rootView.findViewById(R.id.prenomJoueur)

        longueurEditText = rootView.findViewById(R.id.longueurTextj)
        longueurLayoutLogin = rootView.findViewById(R.id.longueur)

        tailleEditText = rootView.findViewById(R.id.tailleTextj)
        tailleLayoutLogin = rootView.findViewById(R.id.taille)

        numEditText = rootView.findViewById(R.id.numTextj)
        numLayoutLogin = rootView.findViewById(R.id.num)

        descriptionEditText = rootView.findViewById(R.id.descJouTextj)
        descriptionLayoutLogin = rootView.findViewById(R.id.descJou)

        addStadeButton = rootView.findViewById(R.id.button55j)

        fab2 = rootView.findViewById(R.id.floatingActionButton3j)

        fab2.setOnClickListener{
            pickImageFromGallery()
        }
        addStadeButton.setOnClickListener {
            if (selectedImageUri0 == null) {
                Toast.makeText(context!!, "Select an Image First ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val nom = emailEditText.text.toString().trim()
            val prenom = prenomEditText.text.toString().trim()
            val age = ageEditText.text.toString().trim()
            val longueur = longueurEditText.text.toString().trim()
            val taille = prenomEditText.text.toString().trim()
            val num = numEditText.text.toString().trim()
            val description = descriptionEditText.text.toString().trim()
            var resolver = requireActivity().contentResolver.openFileDescriptor(selectedImageUri0!!, "r", null) ?: return@setOnClickListener


            doAddEquipe(
                nom,prenom,age,longueur,taille,num,description
            )
            //print(parcelFileDescriptor);
        }
        return rootView
    }

    private fun doAddEquipe(nom: String,  age: String,  prenom: String, longueur: String, taille: String,num:String,description:String){
        if (validate()) {

            if (selectedImageUri0 == null) {

                return
            }


            //val stream = contentResolver.openInputStream(selectedImageUri0!!)
            val stream = requireActivity().contentResolver.openInputStream(selectedImageUri0!!)
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
            data["prenom"] = prenom.toRequestBody(MultipartBody.FORM)
            data["age"] = age.toRequestBody(MultipartBody.FORM)
            data["longueur"] = longueur.toRequestBody(MultipartBody.FORM)
            data["taille"] = taille.toRequestBody(MultipartBody.FORM)
            data["num"] = num.toRequestBody(MultipartBody.FORM)
            data["discription"] = description.toRequestBody(MultipartBody.FORM)
            if (image?.body != null) {

                apiInterface.addjoueur(data, image).enqueue(object :
                    Callback<Joueur> {
                    override fun onResponse(
                        call: Call<Joueur>,
                        response: Response<Joueur>
                    ) {

                        if (response.isSuccessful) {
                            Log.i("onResponse goooood", response.body().toString())
                            //showAlertDialog()
                            Toast.makeText(context, "Joueur Ajouter  ", Toast.LENGTH_SHORT).show()
                            val action = UserCreateJoueurFragmentDirections.actionUserCreateJoueurFragmentToUserJoueurListeFragment()
                            findNavController().navigate(action)



                        } else {
                            Log.i("OnResponse not good", response.body().toString())
                        }
                    }

                    override fun onFailure(call: Call<Joueur>, t: Throwable) {
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
            fab2.hide()

        }
    }    private fun validate(): Boolean {
        emailLayoutLogin.error = null
        ageLayoutLogin.error = null
        prenomLayoutLogin.error = null
        longueurLayoutLogin.error = null
        tailleLayoutLogin.error = null
        numLayoutLogin.error = null
        descriptionLayoutLogin.error = null

        if (emailEditText.text!!.isEmpty()) {
            emailLayoutLogin.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        if (prenomEditText.text!!.isEmpty()) {
            prenomLayoutLogin.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        if (ageEditText.text!!.isEmpty()) {
            ageLayoutLogin.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        if (longueurEditText.text!!.isEmpty()) {
            longueurLayoutLogin.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        if (tailleEditText.text!!.isEmpty()) {
            tailleLayoutLogin.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        if (numEditText.text!!.isEmpty()) {
            numLayoutLogin.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        if (descriptionEditText.text!!.isEmpty()) {
            descriptionLayoutLogin.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        return true
    }


}