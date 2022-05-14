package com.example.sifflet0

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.fragement.LigueFragmentDirections
import com.example.sifflet0.fragement.adapter.LigueAdapter
import com.example.sifflet0.fragement.adapter.StadeAdapter
import com.example.sifflet0.fragement.adapter1
import com.example.sifflet0.models.Equipe
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
    private var arrayList111: List<Ligue>? = null
    lateinit var id_ligueq : String
    lateinit var  sharedPreferences2: SharedPreferences



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
                adapter13.setLigueList(response.body()?.ligues_id)
                arrayList111 = response.body()?.ligues_id

                adapter13.notifyDataSetChanged()
            }


        })




        return rootView
    }

    override fun ClickItem(position: Int) {
        var equipe : Ligue = arrayList111!!.get(position)
        id_ligueq = equipe._id!!


        sharedPreferences2 = requireActivity().getSharedPreferences(PREF_NAME, AppCompatActivity.MODE_PRIVATE);

        //progBar = findViewById(R.id.progressBar)

        if (sharedPreferences2.getBoolean(IS_REMEMBRED, false)) {
            val role: String = sharedPreferences2.getString("ROLE", null)!!

            if (role == "SimpleUser") {
                val action =
                    showligueOfStadeFragmentDirections.actionShowligueOfStadeFragmentToLigueOfStadeDetailsFragment(
                        id_ligueq
                    )
                findNavController().navigate(action)

            } else if (role == "ProprietaireDestade") {
                Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()

            }
        }

    }

}