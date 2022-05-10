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
import com.example.sifflet0.fragement.adapter.LigueAdapter
import com.example.sifflet0.utils.ClickHandler
import com.example.sifflet0.viewModel.ViewModelLigue

lateinit var adapter1: LigueAdapter
lateinit var id_Ligue : String
lateinit var recyclerViewLigue: RecyclerView
class LigueFragment : Fragment(R.layout.fragement_ligue) ,ClickHandler{



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragement_ligue, container, false)
        val button : Button = rootView.findViewById(R.id.add_ligue)
        recyclerViewLigue = rootView.findViewById(R.id.reccycleViewLigue)
        recyclerViewLigue.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        adapter1 = LigueAdapter(this,this)

        recyclerViewLigue.adapter = adapter1
        initViewModel()

        button.setOnClickListener{
            val action = LigueFragmentDirections.actionIcLigueToAddLigueFragment()
            findNavController().navigate(action)

        }



        return rootView

    }

    private fun initViewModel() {
        val viewModel: ViewModelLigue = ViewModelProvider(this).get(ViewModelLigue::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if(it != null) {
                adapter1.setLigueList(it)
                adapter1.notifyDataSetChanged()
            } else {
                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall2(context)
    }


    override fun ClickItem(position: Int) {
        //Toast.makeText(context, "Item $position clicked", Toast.LENGTH_SHORT).show()

        val viewModel: ViewModelLigue = ViewModelProvider(this).get(ViewModelLigue::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if(it != null) {
                id_Ligue = it[position]._id!!
            } else {
                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall2(context)


        val action = LigueFragmentDirections.actionIcLigueToLigueDetailsFragment(id_Ligue)
        findNavController().navigate(action)

    }


}