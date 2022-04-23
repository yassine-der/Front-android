package com.example.sifflet0

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class ligueDetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_ligue_details, container, false)
        val imagewLigueDetails :ImageView = rootView.findViewById(R.id.imageLigueDetails)
        val textLigueDetails :TextView = rootView.findViewById(R.id.textLigueDetails)
        val descriptionLigueDetails :TextView = rootView.findViewById(R.id.decriptionLigueDetails)
        return rootView
    }

}