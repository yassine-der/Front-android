package com.example.sifflet0

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.sifflet0.fragement.adapter.ViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.gestures.OnMapClickListener
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import com.mapbox.maps.viewannotation.ViewAnnotationManager
import com.mapbox.maps.viewannotation.viewAnnotationOptions

class home : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var  buttonNavigator: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        buttonNavigator = findViewById(R.id.buttonNavigationuser)

        //val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_graph) as NavHostFragment
        //val navController = navHostFragment.navController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerViewUser) as NavHostFragment
        navController = navHostFragment.findNavController()

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.ic_stade,R.id.userJoueurListeFragment, R.id.ic_profile)
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