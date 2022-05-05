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
import com.example.sifflet0.fragement.adapter.StadeAdapter
import com.example.sifflet0.utils.CenterZoomLayoutManager
import com.example.sifflet0.utils.ClickHandler
import com.example.sifflet0.viewModel.ViewModelLigue
import com.example.sifflet0.viewModel.mainActitvityViewModel

lateinit var adapter: StadeAdapter
lateinit var id_Stade : String
lateinit var recyclerViewStade: RecyclerView
lateinit var ButtonAddStade: Button
class stade : Fragment()  , ClickHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_stade, container, false)
        recyclerViewStade = rootView.findViewById(R.id.recycle_stade)
        adapter = StadeAdapter(this,this)
        ButtonAddStade = rootView.findViewById(R.id.button2)
        recyclerViewStade.layoutManager = CenterZoomLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerViewStade.adapter = adapter
        initViewModel()
        ButtonAddStade.setOnClickListener {
            val action = stadeDirections.actionIcStadeToAddStadeFragment()
            findNavController().navigate(action)

        }
        return  rootView
    }
    private fun initViewModel() {
        val viewModel: mainActitvityViewModel = ViewModelProvider(this).get(mainActitvityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if(it != null) {
                adapter.setStadeList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall(context)
    }

    override fun ClickItem(position: Int) {
        Toast.makeText(context, "Item $position clicked", Toast.LENGTH_SHORT).show()

        val viewModel: mainActitvityViewModel = ViewModelProvider(this).get(mainActitvityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if(it != null) {
                id_Stade = it[position]._id!!
            } else {
                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall(context)


        val action = stadeDirections.actionIcStadeToDetailStade(id_Stade)
        findNavController().navigate(action)


    }


}