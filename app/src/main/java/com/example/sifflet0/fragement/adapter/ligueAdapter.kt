package com.example.sifflet0.fragement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sifflet0.R
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.models.Ligue
import com.example.sifflet0.utils.ClickHandler

class LigueAdapter(val activity: Fragment,private  val clickHandler: ClickHandler) : RecyclerView.Adapter<LigueAdapter.ligueViewHolder>() {

    private var ligueList: List<Ligue>? = null


    inner class ligueViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val ligueImage :ImageView= itemView.findViewById(R.id.imageCardLigue)
        val ligueName :TextView= itemView.findViewById(R.id.textCardNom)
        fun bind(data: Ligue) {

            ligueName.text = data.nom
            Glide.with(itemView).load(RetrofiteInstance.BASE_URL + data.image).into(ligueImage)

        }
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                clickHandler.ClickItem(position)
            }
        }


    }
    fun setLigueList(ligueList: List<Ligue>?) {
        this.ligueList = ligueList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ligueViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_ligue, parent, false)
        return ligueViewHolder(view)
    }

    override fun onBindViewHolder(holder:ligueViewHolder, position: Int) {
        holder.bind(ligueList?.get(position)!!)

/*
        holder.itemView.setOnClickListener{
            findNavController().navigate(R.id.action_ligueFragment3_to_ligueDetailsFragment)
            val fragmentDetails = ligueDetailsFragment()
            val fragmentlique = LigueFragment()
            val transaction = fragmentlique.childFragmentManager?.beginTransaction()
            transaction?.replace(R.id.nav_graph,fragmentDetails).commit()
        }
*/
    }

    override fun getItemCount(): Int {
        if(ligueList == null)return 0
        else return ligueList?.size!!
    }
}