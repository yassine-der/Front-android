package com.example.sifflet0

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.fragement.equipeDetailsFragmentArgs
import com.example.sifflet0.fragement.id_Stade
import com.example.sifflet0.models.Ligue
import com.example.sifflet0.models.Stade
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class detailStade : Fragment() {
    private  val args : detailStadeArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.activity_detail_stade, container, false)
        val imageDetailsStade :ImageView = rootView.findViewById(R.id.imageView3)
        val nomDetailsStade :TextView = rootView.findViewById(R.id.detailsNomStade)
        val descriptionDetailsStade :TextView = rootView.findViewById(R.id.textView5)
        val apiInterface = RetrofiteInstance.api(context)
        apiInterface.getStadeById(args.idStade).enqueue(object : Callback<Stade> {
            override fun onFailure(call: Call<Stade>, t: Throwable) {
                Toast.makeText(context, "Probleme de connection", Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<Stade>, response: Response<Stade>) {
                if (response.isSuccessful){
                    val stade : Stade = response.body()!!
                    nomDetailsStade.text = stade.nom
                    descriptionDetailsStade.text = stade.discription
                    Glide.with(this@detailStade).load(RetrofiteInstance.BASE_URL + stade.image).into(imageDetailsStade)



                }
            }


        })

        return rootView

    }
}