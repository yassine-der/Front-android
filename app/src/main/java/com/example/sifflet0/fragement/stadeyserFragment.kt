package com.example.sifflet0.fragement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sifflet0.R
import com.example.sifflet0.fragement.adapter.StadeAdapter
import com.example.sifflet0.utils.ClickHandler
import com.example.sifflet0.viewModel.mainActitvityViewModel
import com.example.sifflet0.viewModel.viewModelUser

lateinit var adapter99: StadeAdapter
lateinit var stadeid2 : String
lateinit var recyclerViewStade99: RecyclerView

class stadeyserFragment : Fragment() , ClickHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_stadeyser, container, false)
        recyclerViewStade99 = rootView.findViewById(R.id.recycle_stade_user)
        adapter99 = StadeAdapter(this, this)
        recyclerViewStade99.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )

//recyclerViewStade.layoutManager = CenterZoomLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerViewStade99.adapter = adapter99
        initViewModel()
        return rootView
    }

    private fun initViewModel() {
        val viewModel: viewModelUser =
            ViewModelProvider(this).get(viewModelUser::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter99.setStadeList(it)
                adapter99.notifyDataSetChanged()
            } else {
                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall99(context)
    }

    override fun ClickItem(position: Int) {
        Toast.makeText(context, "Item $position clicked", Toast.LENGTH_SHORT).show()

        val viewModel: mainActitvityViewModel =
            ViewModelProvider(this).get(mainActitvityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                stadeid2 = it[position]._id!!
                val action =
                    stadeyserFragmentDirections.actionStadeyserFragmentToUserDetailsStadeFragment(stadeid2)
                findNavController().navigate(action)

            } else {
                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall77(context)


    }
}