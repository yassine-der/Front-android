package com.example.sifflet0.fragement

import android.app.AlertDialog
import android.content.DialogInterface
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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sifflet0.AddLigueToStadeFragmentArgs
import com.example.sifflet0.R
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.fragement.adapter.EquipeBaseAdapter
import com.example.sifflet0.id_Ligue122
import com.example.sifflet0.models.Equipe
import com.example.sifflet0.models.Ligue
import com.example.sifflet0.models.Stade
import com.example.sifflet0.viewModel.ViewModelLigue
import com.example.sifflet0.viewModel.viewModelEquipe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class addEquipeToLigueFragment : Fragment(),AdapterView.OnItemClickListener{
    lateinit var gridEquipe24: GridView
    private var arrayList24: List<Equipe>? = null
    lateinit var equipeBaseAdapter24 : EquipeBaseAdapter
    private  val args : addEquipeToLigueFragmentArgs by navArgs()

    lateinit var buttonGridAddEquipe24 : Button
    lateinit var id_equipe24 : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_add_equipe_to_ligue, container, false)
        gridEquipe24  = rootView.findViewById(R.id.gridViewequipe24)
        val viewModel: viewModelEquipe = ViewModelProvider(this).get(viewModelEquipe::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if(it != null) {
                //adapter2.setLigueList(it)
                //adapter2.notifyDataSetChanged()
                arrayList24 = it
                equipeBaseAdapter24 = EquipeBaseAdapter(context!!,it)
                gridEquipe24.adapter = equipeBaseAdapter24
                gridEquipe24.onItemClickListener = this@addEquipeToLigueFragment

            } else {
                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall2(context)

        return rootView
    }
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var equipe : Equipe = arrayList24!!.get(position)
        Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
        val viewModel: viewModelEquipe = ViewModelProvider(this).get(viewModelEquipe::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if(it != null) {
                id_equipe24 = it[position]._id!!
            } else {
                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall2(context)

        basicAlert(args.ligueId24, id_equipe24)

    }
    fun basicAlert(idstade:String,idLigue : String){

        val builder = AlertDialog.Builder(context)

        with(builder)
        {

            val positiveButtonClick = { dialog: DialogInterface, which: Int ->


                Toast.makeText(context, "Ajouter", Toast.LENGTH_SHORT).show()

                val apiInterface = RetrofiteInstance.api(context)
                apiInterface.getEquipeById(idLigue).enqueue(object : Callback<Equipe> {
                    override fun onFailure(call: Call<Equipe>, t: Throwable) {
                        Toast.makeText(context, "Probleme de connection", Toast.LENGTH_SHORT).show()

                    }

                    override fun onResponse(call: Call<Equipe>, response: Response<Equipe>) {
                        if (response.isSuccessful){
                            val ligue : Equipe = response.body()!!
                            apiInterface.addEquipeToLigue(idstade,ligue._id!!).enqueue(object :
                                Callback<Ligue> {
                                override fun onFailure(call: Call<Ligue>, t: Throwable) {
                                    Toast.makeText(context, "Probleme de connection", Toast.LENGTH_SHORT).show()

                                }

                                override fun onResponse(call: Call<Ligue>, response: Response<Ligue>) {
                                    if (response.isSuccessful){
                                        val stade : Ligue = response.body()!!
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
            setMessage("Ajouter cette ligue a ce stade?")
            setPositiveButton("Oui", DialogInterface.OnClickListener(function = positiveButtonClick))
            setNegativeButton(android.R.string.no, negativeButtonClick)
            //setNeutralButton("Maybe", neutralButtonClick)
            show()
        }


    }


}
