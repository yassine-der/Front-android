package com.example.sifflet0

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.sifflet0.databinding.AnnotationTextBinding
import com.example.sifflet0.databinding.HomeActivityBinding
import com.example.sifflet0.fragement.adapter.ViewPagerAdapter
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

class home : AppCompatActivity(), OnMapClickListener {
    //@SuppressLint("WrongViewCast")
    private lateinit var mapboxMap: MapboxMap
    private lateinit var viewAnnotationManager: ViewAnnotationManager
    var mapView: MapView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewAnnotationManager = binding.mapView.viewAnnotationManager
        mapboxMap = binding.mapView.getMapboxMap().apply {
            loadStyleUri(Style.MAPBOX_STREETS) {
                addOnMapClickListener(this@home)
                binding.fabStyleToggle.setOnClickListener {
                    when (getStyle()?.styleURI) {
                        Style.MAPBOX_STREETS -> loadStyleUri(Style.SATELLITE_STREETS)
                        Style.SATELLITE_STREETS -> loadStyleUri(Style.MAPBOX_STREETS)
                    }
                }
                Toast.makeText(this@home, STARTUP_TEXT, Toast.LENGTH_LONG).show()
            }
        }


/*
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
        */

    }
/*
    override fun onSupportNavigateUp(): Boolean {
        //val navController = findNavController(R.id.fragmentContainerView3)
        return  navController.navigateUp()|| super.onSupportNavigateUp()
    }
*/
/*
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
    */
private fun bitmapFromDrawableRes(context: Context, @DrawableRes resourceId: Int) =
    convertDrawableToBitmap(AppCompatResources.getDrawable(context, resourceId))

    private fun convertDrawableToBitmap(sourceDrawable: Drawable?): Bitmap? {
        if (sourceDrawable == null) {
            return null
        }
        return if (sourceDrawable is BitmapDrawable) {
            sourceDrawable.bitmap
        } else {
// copying drawable object to not manipulate on the same reference
            val constantState = sourceDrawable.constantState ?: return null
            val drawable = constantState.newDrawable().mutate()
            val bitmap: Bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth, drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        }
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }
/*
    private fun addViewAnnotation(point: Point) {
        // Define the view annotation
        val viewAnnotation = viewAnnotationManager.addViewAnnotation(
            // Specify the layout resource id
            resId = R.layout.annotation_text,
            // Set any view annotation options
            options = viewAnnotationOptions {
                geometry(point)
            }
        )
        AnnotationTextBinding.bind(viewAnnotation)
    }
*/


    @SuppressLint("SetTextI18n")
    private fun addViewAnnotation(point: Point) {
        println("88888888888888888888888888888888888")
        println(point.longitude())
        println("88888888888888888888888888888888888")
        val viewAnnotation = viewAnnotationManager.addViewAnnotation(
            resId = R.layout.annotation_text,
            options = viewAnnotationOptions {
                geometry(point)
                allowOverlap(true)
            }
        )

        AnnotationTextBinding.bind(viewAnnotation).apply {
            annotation.text = "lat=%.2f\nlon=%.2f".format(point.latitude(), point.longitude())
            println("wwwwwwwwwwwwwwwwwwwwwwwwwwwww")
            println(point.latitude())
            println(point.longitude())
            println("wwwwwwwwwwwwwwwwwwwwwwwwwwwww")

            closeNativeView.setOnClickListener {
                viewAnnotationManager.removeViewAnnotation(viewAnnotation)
            }
            selectButton.setOnClickListener { b ->
                val button = b as Button
                val isSelected = button.text.toString().equals("SELECT", true)
                val pxDelta = if (isSelected) SELECTED_ADD_COEF_PX else -SELECTED_ADD_COEF_PX
                button.text = if (isSelected) "DESELECT" else "SELECT"
                viewAnnotationManager.updateViewAnnotation(
                    viewAnnotation,
                    viewAnnotationOptions {
                        selected(isSelected)
                    }
                )
                (button.layoutParams as ViewGroup.MarginLayoutParams).apply {
                    bottomMargin += pxDelta
                    rightMargin += pxDelta
                    leftMargin += pxDelta
                }
                button.requestLayout()
            }
        }
    }

    private companion object {
        const val SELECTED_ADD_COEF_PX = 25
        const val STARTUP_TEXT = "Click on a map to add a view annotation."
    }

    override fun onMapClick(point: Point): Boolean {
        addViewAnnotation(point)
        return true
    }


}