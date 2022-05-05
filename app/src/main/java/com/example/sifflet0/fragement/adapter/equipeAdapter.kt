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
import com.example.sifflet0.models.Equipe
import com.example.sifflet0.utils.ClickHandler

class EquipeAdapter(val activity :Fragment,private  val clickHandler: ClickHandler):RecyclerView.Adapter<EquipeAdapter.EquipeViewHolder>() {

    private var equipeList: List<Equipe>? = null


    inner class EquipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val imageEquipe : ImageView = itemView.findViewById(R.id.imageEquiperecycle)
        val nomEquipe : TextView = itemView.findViewById(R.id.nomEquipeRecycle)
        fun bind(data: Equipe) {

            nomEquipe.text = data.nom
            val replaced = data.image!!.replace("\\", "/")

            Glide.with(itemView).load(RetrofiteInstance.BASE_URL + replaced).into(imageEquipe)
            //GlideToVectorYou.justLoadImage(activity, Uri.parse(data.flag), stadePic)

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
    fun setLigueList(equipeList: List<Equipe>?) {
        this.equipeList = equipeList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardequipe, parent, false)
        return EquipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EquipeViewHolder, position: Int) {
        holder.bind(equipeList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(equipeList == null)return 0
        else return equipeList?.size!!
    }
}