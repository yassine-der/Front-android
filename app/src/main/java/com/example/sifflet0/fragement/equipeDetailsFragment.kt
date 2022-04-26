package com.example.sifflet0.fragement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.sifflet0.R
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.models.Equipe
import com.example.sifflet0.models.Ligue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class equipeDetailsFragment : Fragment() {

    private  val args :equipeDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_equipe_details, container, false)
        val imageDetailsEquipe : ImageView = rootView.findViewById(R.id.imagedetailsEquipe)
        val nomDetailsequipe :TextView = rootView.findViewById(R.id.nomDetailsEquipe)
        val descriptionDetailsequipe :TextView = rootView.findViewById(R.id.descriptionDetailsEquipe)
        val apiInterface = RetrofiteInstance.api(context)
        apiInterface.getEquipeById(args.idEquipe).enqueue(object : Callback<Equipe> {
            override fun onFailure(call: Call<Equipe>, t: Throwable) {
                Toast.makeText(context, "Probleme de connection", Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<Equipe>, response: Response<Equipe>) {
                if (response.isSuccessful){
                    val equipe : Equipe = response.body()!!
                    nomDetailsequipe.text = equipe.nom
                    descriptionDetailsequipe.text = equipe.discription
                    Glide.with(this@equipeDetailsFragment).load(RetrofiteInstance.BASE_URL + equipe.image).into(imageDetailsEquipe)



                }
            }


        })

        return rootView
    }

}