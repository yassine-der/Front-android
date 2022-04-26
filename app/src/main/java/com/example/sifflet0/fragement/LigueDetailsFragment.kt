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
import com.example.sifflet0.models.Ligue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ligueDetailsFragment : Fragment() {

    private  val args :ligueDetailsFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ligue = Ligue()

        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_ligue_details, container, false)
        val imagewLigueDetails :ImageView = rootView.findViewById(R.id.imageLigueDetails)
        val textLigueDetails :TextView = rootView.findViewById(R.id.textLigueDetails)
        val descriptionLigueDetails :TextView = rootView.findViewById(R.id.decriptionLigueDetails)
        val apiInterface = RetrofiteInstance.api(context)
        apiInterface.getLigueById(args.idLigue).enqueue(object : Callback<Ligue> {
            override fun onFailure(call: Call<Ligue>, t: Throwable) {
                Toast.makeText(context, "Probleme de connection", Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<Ligue>, response: Response<Ligue>) {
                if (response.isSuccessful){
                    val ligue : Ligue  = response.body()!!
                    textLigueDetails.text = ligue.nom
                    descriptionLigueDetails.text = ligue.discription
                    Glide.with(this@ligueDetailsFragment).load(RetrofiteInstance.BASE_URL + ligue.image).into(imagewLigueDetails)



                }
            }


        })
        return rootView
    }

}