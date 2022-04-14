package com.example.sifflet0.fragement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sifflet0.R
import com.example.sifflet0.fragement.adapter.StadeAdapter
import com.example.sifflet0.viewModel.mainActitvityViewModel

lateinit var adapter: StadeAdapter

lateinit var recyclerViewStade: RecyclerView
class stade : Fragment() {

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
        adapter = StadeAdapter(this)

        recyclerViewStade.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerViewStade.adapter = adapter
        initViewModel()
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


}