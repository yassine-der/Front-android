package com.example.sifflet0.fragement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.sifflet0.R
import com.example.sifflet0.adapter13
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.fragement.adapter.EquipeBaseAdapter
import com.example.sifflet0.fragement.adapter.joueurBaseAdapterUser
import com.example.sifflet0.models.Equipe
import com.example.sifflet0.models.Joueur
import com.example.sifflet0.models.Stade
import com.example.sifflet0.viewModel.viewModelEquipe
import com.example.sifflet0.viewModel.viewModelJoueur
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserJoueurFragment : Fragment() , AdapterView.OnItemClickListener {
    lateinit var gridEquipe89: GridView
    private var arrayList89: List<Joueur>? = null
    lateinit var equipeBaseAdapter89 : joueurBaseAdapterUser
    lateinit var id_equipe89 : String
    private  val args :  UserJoueurFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_user_joueur, container, false)
        gridEquipe89  = rootView.findViewById(R.id.gridViewJoueur)
        val apiInterface = RetrofiteInstance.api(context)
        apiInterface.getJoueurOfEquipe(args.idEquipe98).enqueue(object : Callback<Equipe> {
            override fun onFailure(call: Call<Equipe>, t: Throwable) {

                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Equipe>, response: Response<Equipe>) {
                arrayList89 = response.body()?.joueurs_id
                equipeBaseAdapter89 = joueurBaseAdapterUser(context!!,arrayList89!!)
                gridEquipe89.adapter = equipeBaseAdapter89
                gridEquipe89.onItemClickListener = this@UserJoueurFragment
            }


        })


        return rootView
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

}