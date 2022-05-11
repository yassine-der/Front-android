package com.example.sifflet0

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.fragement.*
import com.example.sifflet0.fragement.adapter.LigueAdapter
import com.example.sifflet0.fragement.adapter.StadeAdapter
import com.example.sifflet0.models.Ligue
import com.example.sifflet0.models.Stade
import com.example.sifflet0.models.User
import com.example.sifflet0.utils.ClickHandler
import com.example.sifflet0.utils.swipGesture
import com.example.sifflet0.viewModel.ViewModelLigue
import com.example.sifflet0.viewModel.mainActitvityViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

lateinit var adapter14: LigueAdapter
lateinit var id_Ligue122 : String

lateinit var recyclerViewLigue3: RecyclerView

class AddLigueToStadeFragment : Fragment(), ClickHandler {


    private  val args : AddLigueToStadeFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_add_ligue_to_stade, container, false)
        recyclerViewLigue3 = rootView.findViewById(R.id.reccycleViewAddLigueToStade)
        adapter14 = LigueAdapter(this,this)
        recyclerViewLigue3.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        //recyclerViewStade.layoutManager = CenterZoomLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerViewLigue3.adapter = adapter14
        initViewModel()
        return  rootView
    }
    private fun initViewModel() {
        val viewModel: ViewModelLigue = ViewModelProvider(this).get(ViewModelLigue::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if(it != null) {
                adapter14.setLigueList(it)
                adapter14.notifyDataSetChanged()
            } else {
                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall2(context)
    }

    override fun ClickItem(position: Int) {
        Toast.makeText(context, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val viewModel: ViewModelLigue = ViewModelProvider(this).get(ViewModelLigue::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if(it != null) {
                id_Ligue122 = it[position]._id!!
            } else {
                Toast.makeText(context, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall2(context)

        basicAlert(args.idSstaade,id_Ligue122)

    }
    fun basicAlert(idstade:String,idLigue : String){

        val builder = AlertDialog.Builder(context)

        with(builder)
        {

            val positiveButtonClick = { dialog: DialogInterface, which: Int ->


                Toast.makeText(context, "Ajouter", Toast.LENGTH_SHORT).show()

                val apiInterface = RetrofiteInstance.api(context)
                apiInterface.getLigueById(idLigue).enqueue(object : Callback<Ligue> {
                    override fun onFailure(call: Call<Ligue>, t: Throwable) {
                        Toast.makeText(context, "Probleme de connection", Toast.LENGTH_SHORT).show()

                    }

                    override fun onResponse(call: Call<Ligue>, response: Response<Ligue>) {
                        if (response.isSuccessful){
                             val ligue : Ligue    = response.body()!!
                            apiInterface.addLigueToSatde(idstade,ligue._id!!).enqueue(object : Callback<Stade> {
                                override fun onFailure(call: Call<Stade>, t: Throwable) {
                                    Toast.makeText(context, "Probleme de connection", Toast.LENGTH_SHORT).show()

                                }

                                override fun onResponse(call: Call<Stade>, response: Response<Stade>) {
                                    if (response.isSuccessful){
                                        val stade : Stade = response.body()!!
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


