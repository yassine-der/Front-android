package com.example.sifflet0

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sifflet0.fragement.EquipeFragment
import com.example.sifflet0.fragement.LigueFragment
import com.example.sifflet0.fragement.Profile
import com.example.sifflet0.fragement.stade
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityhome : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

lateinit var  buttonNavigator: BottomNavigationView
    private  val stadeFragment = stade()
    private  val profileFragment = Profile()
    private  val ligueFragment = LigueFragment()
    private  val equipeFragment = EquipeFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activityhome)
        //replaceFragement(stadeFragment)
        buttonNavigator = findViewById(R.id.buttonNavigation)
        //val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_graph) as NavHostFragment
        //val navController = navHostFragment.navController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.ic_stade, R.id.ic_ligue, R.id.equipeGrideView2, R.id.ic_profile)
        )


        setupActionBarWithNavController(navController, appBarConfiguration)

        buttonNavigator.setupWithNavController(navController)

    }
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

            return   super.onOptionsItemSelected(item)

        }



    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val view: View? = currentFocus
        if (view != null && (ev.getAction() === MotionEvent.ACTION_UP || ev.getAction() === MotionEvent.ACTION_MOVE) && view is EditText
        ) {
            val scrcoords = IntArray(2)
            view.getLocationOnScreen(scrcoords)
            val x: Float = ev.getRawX() + view.getLeft() - scrcoords[0]
            val y: Float = ev.getRawY() + view.getTop() - scrcoords[1]
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom()) (this.getSystemService(
                INPUT_METHOD_SERVICE
            ) as InputMethodManager).hideSoftInputFromWindow(
                this.window.decorView.applicationWindowToken, 0
            )
        }
        return super.dispatchTouchEvent(ev)
    }



}