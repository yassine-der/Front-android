package com.example.sifflet0.fragement.adapter

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sifflet0.R
import com.example.sifflet0.api.RetrofiteInstance.BASE_URL
import com.example.sifflet0.models.Stade

class StadeAdapter(val activity: Fragment):RecyclerView.Adapter<StadeAdapter.StadeViewHolder>() {

    private var countryList: List<Stade>? = null


    class StadeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
            val stadePic : ImageView = itemView.findViewById(R.id.imageStade)
            //val stadePic1 : ImageView = Glide.with(itemView).load()
            val stadeName :TextView = itemView.findViewById(R.id.nomDuStade)
            val stadeNum :TextView = itemView.findViewById(R.id.numDuSatde)


        fun bind(data: Stade) {

            stadeName.text = data.nom
            Glide.with(itemView).load(BASE_URL + data.image).into(stadePic)
            //tvCapital.text = "Capital: "+data.capital
            //tvRegion.text = "Region: "+data.region

            //GlideToVectorYou.justLoadImage(activity, Uri.parse(data.flag), stadePic)

        }


    }
    fun setStadeList(countryList: List<Stade>?) {
        this.countryList = countryList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StadeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_stade, parent, false)
        return StadeViewHolder(view)

    }

    override fun onBindViewHolder(holder: StadeViewHolder, position: Int) {
        holder.bind(countryList?.get(position)!!)

    }

    override fun getItemCount(): Int {
        if(countryList == null)return 0
        else return countryList?.size!!
    }

}