package com.example.sifflet0.fragement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sifflet0.R
import com.example.sifflet0.fragement.adapter.EquipeAdapter
import com.example.sifflet0.utils.ClickHandler
import com.example.sifflet0.viewModel.ViewModelLigue
import com.example.sifflet0.viewModel.viewModelEquipe

lateinit var id_equipe : String
lateinit var adapter2:EquipeAdapter
lateinit var recyclerViewequipe: RecyclerView
class EquipeFragment : Fragment() , ClickHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_equipe, container, false);
        recyclerViewequipe = rootView.findViewById(R.id.recycleViewequipe)
        recyclerViewequipe.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        adapter2 = EquipeAdapter(this,this)
        recyclerViewequipe.adapter = adapter2
        initViewModel()
        return rootView
    }
    private fun initViewModel() {
        val viewModel: viewModelEquipe = ViewModelProvider(this).get(viewModelEquipe::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if(it != null) {
                adapter2.setLigueList(it)
                adapter2.notifyDataSetChanged()
            } else {
                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall2(context)
    }

    override fun ClickItem(position: Int) {

        val viewModel: viewModelEquipe = ViewModelProvider(this).get(viewModelEquipe::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if(it != null) {
                id_equipe = it[position]._id!!
            } else {
                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall2(context)


        val action = EquipeFragmentDirections.actionIcEquipeToEquipeDetailsFragment(id_equipe)
        findNavController().navigate(action)


    }

    }