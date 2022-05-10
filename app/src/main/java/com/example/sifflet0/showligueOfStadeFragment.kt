package com.example.sifflet0

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.fragement.LigueFragmentDirections
import com.example.sifflet0.fragement.adapter.LigueAdapter
import com.example.sifflet0.fragement.adapter.StadeAdapter
import com.example.sifflet0.fragement.adapter1
import com.example.sifflet0.models.Ligue
import com.example.sifflet0.models.Stade
import com.example.sifflet0.utils.ClickHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

lateinit var adapter13: LigueAdapter
lateinit var id_Ligue : String
lateinit var recyclerViewLigue2: RecyclerView

class showligueOfStadeFragment : Fragment() , ClickHandler {
    private  val args : showligueOfStadeFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_showligue_of_stade, container, false)
        recyclerViewLigue2 = rootView.findViewById(R.id.reccycleViewLigueofStade)
        recyclerViewLigue2.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)
        adapter13 = LigueAdapter(this,this)

        recyclerViewLigue2.adapter = adapter13
        val apiInterface = RetrofiteInstance.api(context)
        apiInterface.getLigueStadeById(args.idSstade).enqueue(object : Callback<Stade> {
            override fun onFailure(call: Call<Stade>, t: Throwable) {
                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Stade>, response: Response<Stade>) {
                println(response.body())
                adapter13.setLigueList(response.body()?.ligues_id)
                adapter13.notifyDataSetChanged()
            }


        })




        return rootView
    }

    override fun ClickItem(position: Int) {
        Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()

    }

}