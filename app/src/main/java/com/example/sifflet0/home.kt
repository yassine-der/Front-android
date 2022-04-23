package com.example.sifflet0

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
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
        //setupActionBarWithNavController(findNavController(R.id.fragmentContainerView))

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
    }
/*
    override fun onSupportNavigateUp(): Boolean {
        //val navController = findNavController(R.id.fragmentContainerView3)
        return  navController.navigateUp()|| super.onSupportNavigateUp()
    }
*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu0, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.mLogout -> {
                //TODO 5 "Clear the SharedPreferences file and destroy the activity"
                getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit().clear().apply()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}