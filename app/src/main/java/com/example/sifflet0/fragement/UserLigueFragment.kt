package com.example.sifflet0.fragement

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
import com.example.sifflet0.*
import com.example.sifflet0.adapter13
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.fragement.adapter.LigueAdapter
import com.example.sifflet0.models.Ligue
import com.example.sifflet0.models.Stade
import com.example.sifflet0.recyclerViewLigue2
import com.example.sifflet0.utils.ClickHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


lateinit var adapter77: LigueAdapter
lateinit var recyclerViewLigue77: RecyclerView

class UserLigueFragment : Fragment() , ClickHandler {

    private  val args : UserLigueFragmentArgs by navArgs()
    private var arrayList177: List<Ligue>? = null
    lateinit var id_ligueq77 : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =inflater.inflate(R.layout.fragment_user_ligue, container, false)
        recyclerViewLigue77 = rootView.findViewById(R.id.reccycleViewLigueofStadeUser)
        recyclerViewLigue77.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)
        adapter77 = LigueAdapter(this,this)

        recyclerViewLigue77.adapter = adapter77
        val apiInterface = RetrofiteInstance.api(context)
        apiInterface.getLigueStadeById(args.idSstade).enqueue(object : Callback<Stade> {
            override fun onFailure(call: Call<Stade>, t: Throwable) {
                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Stade>, response: Response<Stade>) {
                adapter77.setLigueList(response.body()?.ligues_id)
                arrayList177 = response.body()?.ligues_id

                adapter77.notifyDataSetChanged()
            }


        })




        return rootView
    }

    override fun ClickItem(position: Int) {
        Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
        var equipe : Ligue = arrayList177!!.get(position)
        id_ligueq77 = equipe._id!!

        val action = UserLigueFragmentDirections.actionUserLigueFragmentToUserDetailsLigueFragment( id_ligueq77)
        findNavController().navigate(action)

    }
}