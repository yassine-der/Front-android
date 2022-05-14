package com.example.sifflet0.fragement

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sifflet0.*
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.fragement.adapter.EquipeBaseAdapter
import com.example.sifflet0.fragement.adapter.equipeBaseAdapterLigue
import com.example.sifflet0.models.*
import com.example.sifflet0.viewModel.viewModelEquipe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class   showEquipeOfLigueFragment : Fragment(), AdapterView.OnItemClickListener {
    lateinit var gridEquipe13: GridView
    private var arrayList13352: List<EquipeVraiResponse>? = null
    lateinit var equipeBaseAdapter13 : equipeBaseAdapterLigue
    lateinit var id_equipe152 : String
    lateinit var  sharedPreferences: SharedPreferences

    private  val args :showEquipeOfLigueFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =inflater.inflate(R.layout.fragment_show_equipe_of_ligue, container, false)
        gridEquipe13  = rootView.findViewById(R.id.gridViewequipeequipe)
        val apiInterface = RetrofiteInstance.api(context)
        apiInterface.getEquipeLigueById(args.idOfEquipe).enqueue(object : Callback<LigueResponse> {
            override fun onFailure(call: Call<LigueResponse>, t: Throwable) {
                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<LigueResponse>, response: Response<LigueResponse>) {
                equipeBaseAdapter13 = equipeBaseAdapterLigue(context!!,response.body()?.equipes_ids!!)
                gridEquipe13.adapter = equipeBaseAdapter13

                arrayList13352 = response.body()?.equipes_ids
                gridEquipe13.onItemClickListener = this@showEquipeOfLigueFragment

            }


        })

        return  rootView
    }


    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        sharedPreferences = requireActivity().getSharedPreferences(PREF_NAME, AppCompatActivity.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(IS_REMEMBRED, false)){
            val role :String = sharedPreferences.getString("ROLE", null)!!

            if(role == "SimpleUser" )
            {
                var equipe : EquipeVraiResponse = arrayList13352!!.get(position)
                id_equipe152 = equipe._id!!

                val action = showEquipeOfLigueFragmentDirections.actionShowEquipeOfLigueFragment2ToUserEquipeDetailsFragment(id_equipe152)
                findNavController().navigate(action)

            }
            else if(role == "ProprietaireDestade" ){

            }

        }



    }

}