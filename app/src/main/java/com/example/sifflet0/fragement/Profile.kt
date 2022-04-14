package com.example.sifflet0.fragement

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.sifflet0.R
import com.example.sifflet0.api.RetrofiteInstance

class Profile : Fragment() {
    lateinit var nom :  TextView
    lateinit var prenom :  TextView
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
        imageView = rootView.findViewById(R.id.profileImage)
        mSharedPref = requireActivity().getSharedPreferences("LOGIN_PREF", AppCompatActivity.MODE_PRIVATE);
        val nomStr: String = mSharedPref.getString("NOM", null).toString()
        val prenomStr: String = mSharedPref.getString("PRENOM", null).toString()
        val emailStr: String = mSharedPref.getString("EMAIL", null).toString()
        val imageStr: String = mSharedPref.getString("IMAGE2", null).toString()

        val index = 7

        val imageStr1: String = imageStr.substring(0, index) + '/' + imageStr.substring(index + 1)


        nom.text = nomStr
        prenom.text = prenomStr
        email.text = emailStr
        Glide.with(this).load(RetrofiteInstance.BASE_URL + imageStr1).into(imageView)

        // Inflate the layout for this fragment
        return rootView
    }

}