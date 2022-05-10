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
import com.bumptech.glide.Glide
import com.example.sifflet0.R
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class profileUserFragment : Fragment() {
    lateinit var nom : TextView
    lateinit var prenom : TextView
    lateinit var button: Button
    lateinit var email : TextView
    lateinit var imageView: ImageView
    lateinit var mSharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_profile_user, container, false)
        nom = rootView.findViewById(R.id.profileNomUser)
        prenom = rootView.findViewById(R.id.profilePrenomuser)
        email = rootView.findViewById(R.id.profileEmailUser)
        imageView = rootView.findViewById(R.id.profileImageUser)

        mSharedPref = requireActivity().getSharedPreferences("LOGIN_PREF", AppCompatActivity.MODE_PRIVATE);
        val idUser: String = mSharedPref.getString("USER_ID", null).toString()

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
                    val replaced = user.image!!.replace("\\", "/")

                    Glide.with(this@profileUserFragment).load(RetrofiteInstance.BASE_URL + replaced).into(imageView)



                }
            }


        })
        return rootView
    }

}