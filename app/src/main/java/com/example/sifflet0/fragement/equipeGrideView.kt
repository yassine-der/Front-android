package com.example.sifflet0.fragement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sifflet0.R
import com.example.sifflet0.fragement.adapter.EquipeBaseAdapter
import com.example.sifflet0.models.Equipe
import com.example.sifflet0.viewModel.viewModelEquipe
import android.R.id





class equipeGrideView: Fragment() ,AdapterView.OnItemClickListener{
lateinit var gridEquipe: GridView
private var arrayList: List<Equipe>? = null
lateinit var equipeBaseAdapter : EquipeBaseAdapter
    lateinit var id_equipe : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.gride_view_equipe, container, false)

         gridEquipe  = rootView.findViewById(R.id.gridViewequipe)
        val viewModel: viewModelEquipe = ViewModelProvider(this).get(viewModelEquipe::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if(it != null) {
                //adapter2.setLigueList(it)
                //adapter2.notifyDataSetChanged()
                    arrayList = it
                equipeBaseAdapter = EquipeBaseAdapter(context!!,it)
                gridEquipe.adapter = equipeBaseAdapter
                gridEquipe.onItemClickListener = this@equipeGrideView

            } else {
                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall2(context)


        return  rootView
    }


    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var equipe : Equipe = arrayList!!.get(position)
        Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()

        id_equipe = equipe._id!!
        val action = equipeGrideViewDirections.actionEquipeGrideView2ToEquipeDetailsFragment2(
            id_equipe)
        findNavController().navigate(action)



    }

}