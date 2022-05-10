package com.example.sifflet0.fragement

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.sifflet0.R
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.models.User
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class updateProfileFragment : Fragment() {
    lateinit var nomUpdate: TextInputEditText
    lateinit var nomLayoutUpdate: TextInputLayout

    lateinit var prenomUpdate: TextInputEditText
    lateinit var prenomLayoutUpdate: TextInputLayout


    lateinit var confirmePasswordUpdate: TextInputEditText
    lateinit var confirmePasswordLayoutUpdate: TextInputLayout

    lateinit var passwordUpdate: TextInputEditText
    lateinit var passwordLayoutUpdate: TextInputLayout

    lateinit var imageUpdate: ImageView

    lateinit var updatebutton: Button



    lateinit var mSharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_update_profile, container, false)
        nomUpdate = rootView.findViewById(R.id.nomUpdateText)
        nomLayoutUpdate = rootView.findViewById(R.id.nomUpdate)
        prenomUpdate = rootView.findViewById(R.id.prenomUpdateText)
        prenomLayoutUpdate = rootView.findViewById(R.id.prenomUpdate)
        confirmePasswordUpdate = rootView.findViewById(R.id.confirmePasswordUapadteText)
        confirmePasswordLayoutUpdate = rootView.findViewById(R.id.confirmePasswordUapadte)
        passwordUpdate = rootView.findViewById(R.id.passwordUapadteText)
        passwordLayoutUpdate = rootView.findViewById(R.id.passwordUapadte)
        updatebutton = rootView.findViewById(R.id.button3)
        imageUpdate = rootView.findViewById(R.id.imageView4)
        mSharedPref = requireActivity().getSharedPreferences("LOGIN_PREF", AppCompatActivity.MODE_PRIVATE);
        val nomStr: String = mSharedPref.getString("NOM", null).toString()
        val prenomStr: String = mSharedPref.getString("PRENOM", null).toString()
        val imageStr: String = mSharedPref.getString("IMAGE2", null).toString()
        val idUser: String = mSharedPref.getString("USER_ID", null).toString()
        //nomUpdate.text = nomStr.
        //prenomUpdate.text = prenomStr
        Glide.with(this).load(RetrofiteInstance.BASE_URL + imageStr).into(imageUpdate)
        updatebutton.setOnClickListener {

            val nom = nomUpdate.text.toString().trim()
            val prenom = prenomUpdate.text.toString().trim()
            val password = passwordUpdate.text.toString().trim()
            val confirmPassword = confirmePasswordUpdate.text.toString().trim()

            if (validate()) {
                //val apiInterface = UserApi.create()
                val apiInterface = RetrofiteInstance.api(context)
                //progBar.visibility = View.VISIBLE

                var user = User()
                user.nom = nom
                user.prenom = prenom
                user.motdepasse = password
                apiInterface.updateProfile(
                    user
                ).enqueue(object :
                    Callback<User> {

                    override fun onResponse(call: Call<User>, response: Response<User>) {

                        val user = response.body()



                            Toast.makeText(context, "Update Success", Toast.LENGTH_SHORT).show()
                            val action = updateProfileFragmentDirections.actionUpdateProfileFragmentToIcProfile()
                            findNavController().navigate(action)


                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {

                        Toast.makeText(context, "Connexion error!", Toast.LENGTH_SHORT).show()

                    }

                })

            }



        }

        return rootView
    }
    private fun validate(): Boolean {
        prenomLayoutUpdate.error = null
        nomLayoutUpdate.error = null
        confirmePasswordUpdate.error = null
        passwordLayoutUpdate.error = null

        if (nomUpdate.text!!.isEmpty()) {
            nomLayoutUpdate.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        if (prenomUpdate.text!!.isEmpty()) {
            prenomLayoutUpdate.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        if (confirmePasswordUpdate.text!!.isEmpty()) {
            confirmePasswordLayoutUpdate.error = getString(R.string.mustNotBeEmpty)
            return false
        }

        if (passwordUpdate.text!!.isEmpty()) {
            passwordLayoutUpdate.error = getString(R.string.mustNotBeEmpty)
            return false
        }
        if (passwordUpdate.text!!.length < 8) {
            passwordLayoutUpdate.setError("Password Length must be more than " + 8 + "characters")
            return false
        }

        // Checking if repeat password is same
        if (!passwordUpdate.text.toString().equals(confirmePasswordUpdate.text.toString())) {
            confirmePasswordLayoutUpdate.setError("Password does not match")
            return false
        }


        return true
    }



}