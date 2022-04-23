package com.example.sifflet0.fragement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.sifflet0.R
import com.example.sifflet0.fragement.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class viewPagerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_view_pager, container, false)
        val tableLayout = rootView.findViewById<TabLayout>(R.id.tabLayout1)
        val viewPager1 = rootView.findViewById<ViewPager2>(R.id.viewPager1)
        val adapter = ViewPagerAdapter(this.childFragmentManager,lifecycle)
        //setupActionBarWithNavController(findNavController(R.id.fragmentContainerView3))

        viewPager1.adapter = adapter
        TabLayoutMediator(tableLayout,viewPager1){
                tab,position->
            when(position){
                0->{
                    tab.text = "Profile"
                }
                1->{
                    tab.text = "Stade"
                }
                2->{
                    tab.text = "Ligue"
                }
                3->{
                    tab.text = "Equipe"
                }
            }
        }.attach()

        return rootView
    }

}