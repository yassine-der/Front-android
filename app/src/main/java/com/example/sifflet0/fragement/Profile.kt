package com.example.sifflet0.fragement

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.sifflet0.R
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.models.Ligue
import com.example.sifflet0.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Profile : Fragment() {
    lateinit var nom :  TextView
    lateinit var prenom :  TextView
    lateinit var button: Button
    lateinit var email :  TextView
    lateinit var imageView: ImageView
    lateinit var mSharedPref :SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)
        nom = rootView.findViewById(R.id.profileNom)
        prenom = rootView.findViewById(R.id.profilePrenom)
        email = rootView.findViewById(R.id.profileEmail)
        button = rootView.findViewById(R.id.button23)
        imageView = rootView.findViewById(R.id.profileImage)

        mSharedPref = requireActivity().getSharedPreferences("LOGIN_PREF", AppCompatActivity.MODE_PRIVATE);
        val idUser: String = mSharedPref.getString("USER_ID", null).toString()

        /*
        val nomStr: String = mSharedPref.getString("NOM", null).toString()
        val prenomStr: String = mSharedPref.getString("PRENOM", null).toString()
        val emailStr: String = mSharedPref.getString("EMAIL", null).toString()
        val imageStr: String = mSharedPref.getString("IMAGE2", null).toString()
        println("***********************************")
        println(imageStr)
        println("***********************************")

        val index = 7

        val imageStr1: String = imageStr.substring(0, index) + '/' + imageStr.substring(index + 1)
*/
        val apiInterface = RetrofiteInstance.api(context)
        apiInterface.getProfile(idUser).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(context, "Probleme de connection", Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    val user : User = response.body()!!
                    nom.text = user.nom
                    prenom.text = user.prenom
                    email.text = user.email
                    Glide.with(this@Profile).load(RetrofiteInstance.BASE_URL + user.image).into(imageView)


                }
            }


        })

        button.setOnClickListener {
            val action = ProfileDirections.actionIcProfileToUpdateProfileFragment()
            findNavController().navigate(action)


        }
        // Inflate the layout for this fragment
        return rootView
    }

}