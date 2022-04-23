package com.example.sifflet0.fragement.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sifflet0.fragement.EquipeFragment
import com.example.sifflet0.fragement.LigueFragment
import com.example.sifflet0.fragement.Profile
import com.example.sifflet0.fragement.stade

class ViewPagerAdapter(supportFragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(supportFragmentManager,
    lifecycle) {
    override fun getItemCount(): Int {
        return  4

    }

    override fun createFragment(position: Int): Fragment {
        return when(position){

            0->{
            Profile()
            }
            1->{
            stade()
            }
            2->{
            LigueFragment()
            }
            3->{
            EquipeFragment()
            }
            else->{
                Fragment()
            }
        }


    }

}