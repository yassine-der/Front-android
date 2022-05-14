package com.example.sifflet0.fragement

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sifflet0.AddLigueToStadeFragmentArgs
import com.example.sifflet0.R
import com.example.sifflet0.UserJoueurListeFragmentDirections
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.fragement.adapter.joueurBaseAdapterUser
import com.example.sifflet0.id_Ligue122
import com.example.sifflet0.models.Equipe
import com.example.sifflet0.models.Joueur
import com.example.sifflet0.models.Ligue
import com.example.sifflet0.models.Stade
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserAddJoueurToEquipeFragment : Fragment(), AdapterView.OnItemClickListener {
    lateinit var gridEquipe86: GridView
    private var arrayList86: List<Joueur>? = null
    lateinit var id_Ligue186 : String

    lateinit var equipeBaseAdapter86 : joueurBaseAdapterUser
    lateinit var id_equipe89 : String
    private  val args : UserAddJoueurToEquipeFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =inflater.inflate(R.layout.fragment_user_add_joueur_to_equipe, container, false)
        gridEquipe86  = rootView.findViewById(R.id.gridViewJoueurListadd)
        val apiInterface = RetrofiteInstance.api(context)
        apiInterface.getMyJoueur().enqueue(object : Callback<List<Joueur>> {
            override fun onFailure(call: Call<List<Joueur>>, t: Throwable) {

                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Joueur>>, response: Response<List<Joueur>>) {
                arrayList86 = response.body()
                equipeBaseAdapter86 = joueurBaseAdapterUser(context!!,arrayList86!!)
                gridEquipe86.adapter = equipeBaseAdapter86
                gridEquipe86.onItemClickListener = this@UserAddJoueurToEquipeFragment

            }


        })


        return rootView
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val apiInterface = RetrofiteInstance.api(context)
        apiInterface.getMyJoueur().enqueue(object : Callback<List<Joueur>> {
            override fun onFailure(call: Call<List<Joueur>>, t: Throwable) {

                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Joueur>>, response: Response<List<Joueur>>) {
                arrayList86 = response.body()
                equipeBaseAdapter86 = joueurBaseAdapterUser(context!!,arrayList86!!)
                gridEquipe86.adapter = equipeBaseAdapter86
                gridEquipe86.onItemClickListener = this@UserAddJoueurToEquipeFragment
                id_Ligue186 = arrayList86!![position]._id

                basicAlert(args.equipeid86, id_Ligue186)


            }


        })


    }
    fun basicAlert(idstade:String,idLigue : String){

        val builder = AlertDialog.Builder(context)

        with(builder)
        {

            val positiveButtonClick = { dialog: DialogInterface, which: Int ->


                Toast.makeText(context, "Ajouter", Toast.LENGTH_SHORT).show()

                val apiInterface = RetrofiteInstance.api(context)
                apiInterface.getJoueurById(idLigue).enqueue(object : Callback<Joueur> {
                    override fun onFailure(call: Call<Joueur>, t: Throwable) {
                        Toast.makeText(context, "Probleme de connection", Toast.LENGTH_SHORT).show()

                    }

                    override fun onResponse(call: Call<Joueur>, response: Response<Joueur>) {
                        if (response.isSuccessful){
                            val ligue : Joueur = response.body()!!
                            apiInterface.addJoueurToEquipe(idstade,ligue._id!!).enqueue(object : Callback<Equipe> {
                                override fun onFailure(call: Call<Equipe>, t: Throwable) {
                                    Toast.makeText(context, "Probleme de connection", Toast.LENGTH_SHORT).show()

                                }

                                override fun onResponse(call: Call<Equipe>, response: Response<Equipe>) {
                                    if (response.isSuccessful){
                                        val stade : Equipe = response.body()!!
                                        Toast.makeText(context, "Ajouter", Toast.LENGTH_SHORT).show()
                                    }
                                }


                            })


                        }
                    }


                })



            }
            val negativeButtonClick = { dialog: DialogInterface, which: Int ->
                Toast.makeText(context,
                    android.R.string.no, Toast.LENGTH_SHORT).show()
            }

            setTitle("Ajout")
            setMessage("Ajouter Ce joueur a cette equipe?")
            setPositiveButton("Oui", DialogInterface.OnClickListener(function = positiveButtonClick))
            setNegativeButton(android.R.string.no, negativeButtonClick)
            //setNeutralButton("Maybe", neutralButtonClick)
            show()
        }


    }


}
