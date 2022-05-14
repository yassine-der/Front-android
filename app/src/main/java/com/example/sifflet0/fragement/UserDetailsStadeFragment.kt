package com.example.sifflet0.fragement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.sifflet0.*
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.models.Stade
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailsStadeFragment : Fragment() {
    private  val args : UserDetailsStadeFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =inflater.inflate(R.layout.fragment_user_details_stade, container, false)
        val imageDetailsStade : ImageView = rootView.findViewById(R.id.imageView342)
        val nomDetailsStade : TextView = rootView.findViewById(R.id.detailsNomStade42)
        val numstade : TextView = rootView.findViewById(R.id.textView242)
        val descriptionDetailsStade : TextView = rootView.findViewById(R.id.textView542)
        val buttonMap : Button = rootView.findViewById(R.id.buttonMap42)
        val ShowLigue : Button = rootView.findViewById(R.id.buttonMap428)
        val apiInterface = RetrofiteInstance.api(context)
        apiInterface.getStadeById(args.stadeid2).enqueue(object : Callback<Stade> {
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
                    //numstade.text = stade.num!!
                    val id_Stade77 :String = stade._id!!
                    descriptionDetailsStade.text = stade.discription
                    val replaced = stade.image!!.replace("\\", "/")

                    Glide.with(this@UserDetailsStadeFragment).load(RetrofiteInstance.BASE_URL + replaced).into(imageDetailsStade)
                    buttonMap.setOnClickListener {
                        val action = UserDetailsStadeFragmentDirections.actionUserDetailsStadeFragmentToMapBoxFragment2(lat_Stade,long_Stade,name)
                        findNavController().navigate(action)

                    }

                    ShowLigue.setOnClickListener {
                        val action = UserDetailsStadeFragmentDirections.actionUserDetailsStadeFragmentToUserLigueFragment(id_Stade77)
                        findNavController().navigate(action)

                    }


                }
            }


        })


        return rootView
    }

}