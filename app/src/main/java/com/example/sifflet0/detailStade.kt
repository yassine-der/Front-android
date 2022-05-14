package com.example.sifflet0

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.models.Stade
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

lateinit var lat_Stade : String
lateinit var id_Stade : String
lateinit var long_Stade : String
lateinit var name : String

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
        val numstade :TextView = rootView.findViewById(R.id.textView2)
        val descriptionDetailsStade :TextView = rootView.findViewById(R.id.textView5)
        val buttonMap : Button = rootView.findViewById(R.id.buttonMap)
        val ShowLigue : Button = rootView.findViewById(R.id.buttonShowligue)
        val buttonAddLigue : Button = rootView.findViewById(R.id.buttonAddLigue)
        val apiInterface = RetrofiteInstance.api(context)
        apiInterface.getStadeById(args.idStade).enqueue(object : Callback<Stade> {
            override fun onFailure(call: Call<Stade>, t: Throwable) {
                Toast.makeText(context, "Probleme de connection", Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<Stade>, response: Response<Stade>) {
                if (response.isSuccessful){
                    val stade : Stade = response.body()!!

                    nomDetailsStade.text = stade.nom
                    lat_Stade = stade.lat!!
                    long_Stade = stade.lon!!
                    name = stade.nom!!
                   // numstade.text = stade.num!!
                    descriptionDetailsStade.text = stade.discription
                    val replaced = stade.image!!.replace("\\", "/")

                    Glide.with(this@detailStade).load(RetrofiteInstance.BASE_URL + replaced).into(imageDetailsStade)

                    buttonMap.setOnClickListener {
                        val action = detailStadeDirections.actionDetailStadeToMapBoxFragment(lat_Stade,long_Stade,name)
                        findNavController().navigate(action)

                    }

                    ShowLigue.setOnClickListener {
                        val action = detailStadeDirections.actionDetailStadeToShowligueOfStadeFragment(id_Stade)
                        findNavController().navigate(action)

                    }
                    buttonAddLigue.setOnClickListener {
                        val action = detailStadeDirections.actionDetailStadeToAddLigueToStadeFragment(id_Stade)
                        findNavController().navigate(action)

                    }



                }
            }


        })

        return rootView

    }
}