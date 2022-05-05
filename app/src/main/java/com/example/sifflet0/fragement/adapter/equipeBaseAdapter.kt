package com.example.sifflet0.fragement.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.sifflet0.R
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.fragement.equipeGrideView
import com.example.sifflet0.models.Equipe
import com.example.sifflet0.models.Stade
import com.example.sifflet0.utils.ClickHandler

class EquipeBaseAdapter (var context:Context,var arrayList: List<Equipe>): BaseAdapter() {
    override fun getCount(): Int {
        return  arrayList.size
    }

    override fun getItem(position: Int): Any {
        return  arrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return  position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view : View = View.inflate(context, R.layout.card_grid_equipe,null)

        var logo :ImageView = view.findViewById(R.id.imageEquiperecycle88)
        var nom: TextView =view.findViewById(R.id.nomEquipeRecycle88)

        var equipe : Equipe = arrayList.get(position)

        nom.text = equipe.nom
        val replaced = equipe.image!!.replace("\\", "/")

        Glide.with(view).load(RetrofiteInstance.BASE_URL + replaced).into(logo)

        return  view
    }
}