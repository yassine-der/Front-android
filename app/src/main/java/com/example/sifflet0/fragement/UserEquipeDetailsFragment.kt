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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.sifflet0.IS_REMEMBRED
import com.example.sifflet0.PREF_NAME
import com.example.sifflet0.R
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.models.Equipe
import com.example.sifflet0.showligueOfStadeFragmentDirections
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserEquipeDetailsFragment : Fragment() {


    private  val args :UserEquipeDetailsFragmentArgs by navArgs()
    lateinit var  sharedPreferences2: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_user_equipe_details, container, false)
        val imageDetailsEquipe : ImageView = rootView.findViewById(R.id.imagedetailsEquipe52)
        val nomDetailsequipe : TextView = rootView.findViewById(R.id.nomDetailsEquipe52)
        val descriptionDetailsequipe : TextView = rootView.findViewById(R.id.descriptionDetailsEquipe52)
        val buttonToJoueur :Button =rootView.findViewById(R.id.button8)
        val addjoueur :Button =rootView.findViewById(R.id.button9)
        val apiInterface = RetrofiteInstance.api(context)
        apiInterface.getEquipeById(args.idequipe52).enqueue(object : Callback<Equipe> {
            override fun onFailure(call: Call<Equipe>, t: Throwable) {
                Toast.makeText(context, "Probleme de connection", Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<Equipe>, response: Response<Equipe>) {
                if (response.isSuccessful) {
                    val equipe: Equipe = response.body()!!
                    println("________________________")
                    println(response.body()!!)
                    println("________________________")

                    nomDetailsequipe.text = equipe.nom
                    descriptionDetailsequipe.text = equipe.discription
                    val replaced = equipe.image!!.replace("\\", "/")

                    Glide.with(this@UserEquipeDetailsFragment).load(RetrofiteInstance.BASE_URL + replaced).into(imageDetailsEquipe)



                    buttonToJoueur.setOnClickListener {
                        sharedPreferences2 = requireActivity().getSharedPreferences(
                            PREF_NAME,
                            AppCompatActivity.MODE_PRIVATE
                        );

                        //progBar = findViewById(R.id.progressBar)

                        if (sharedPreferences2.getBoolean(IS_REMEMBRED, false)) {
                            val role: String = sharedPreferences2.getString("ROLE", null)!!

                            if (role == "SimpleUser") {
                                val action =
                                    UserEquipeDetailsFragmentDirections.actionUserEquipeDetailsFragmentToUserJoueurFragment(
                                        equipe._id!!
                                    )
                                findNavController().navigate(action)

                            } else if (role == "ProprietaireDestade") {
                                Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()

                            }

                        }


                    }
                    addjoueur.setOnClickListener {
                        val action =
                            UserEquipeDetailsFragmentDirections.actionUserEquipeDetailsFragmentToUserAddJoueurToEquipeFragment(
                                equipe._id!!
                            )
                        findNavController().navigate(action)


                    }
                }
            }

        })

        return rootView
    }


}