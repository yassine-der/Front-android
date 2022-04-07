package com.example.sifflet0

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.sifflet0.fragement.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class home : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        val tableLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager1 = findViewById<ViewPager2>(R.id.viewPager)
        val adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
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
            }
        }.attach()
    }
}