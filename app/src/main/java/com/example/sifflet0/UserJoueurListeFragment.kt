package com.example.sifflet0

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.fragement.adapter.joueurBaseAdapterUser
import com.example.sifflet0.fragement.ajouter_stade_FragmentDirections
import com.example.sifflet0.models.Equipe
import com.example.sifflet0.models.Joueur
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserJoueurListeFragment : Fragment() , AdapterView.OnItemClickListener{
    lateinit var gridEquipe89: GridView
    private var arrayList89: List<Joueur>? = null
    lateinit var equipeBaseAdapter87 : joueurBaseAdapterUser
    lateinit var id_equipe89 : String
    lateinit var addJ : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_user_joueur_liste, container, false)
        gridEquipe89  = rootView.findViewById(R.id.gridViewJoueurList)
        addJ  = rootView.findViewById(R.id.button10)
        val apiInterface = RetrofiteInstance.api(context)
        apiInterface.getMyJoueur().enqueue(object : Callback<List<Joueur>> {
            override fun onFailure(call: Call<List<Joueur>>, t: Throwable) {

                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Joueur>>, response: Response<List<Joueur>>) {
                arrayList89 = response.body()
                equipeBaseAdapter87 = joueurBaseAdapterUser(context!!,arrayList89!!)
                gridEquipe89.adapter = equipeBaseAdapter87
                gridEquipe89.onItemClickListener = this@UserJoueurListeFragment
                addJ.setOnClickListener {
                    val action = UserJoueurListeFragmentDirections.actionUserJoueurListeFragmentToUserCreateJoueurFragment()
                    findNavController().navigate(action)

                }
            }


        })


        return rootView
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

}