package com.example.sifflet0.fragement

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Annotation
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.fragment.navArgs
import com.example.sifflet0.R
import com.example.sifflet0.databinding.FragmentMapBoxBinding
import com.example.sifflet0.databinding.AnnotationTextBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.Style
import com.mapbox.maps.extension.style.expressions.dsl.generated.interpolate
import com.mapbox.maps.plugin.LocationPuck2D
import com.mapbox.maps.plugin.PuckBearingSource
import com.mapbox.maps.plugin.animation.camera
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.*
import com.mapbox.maps.plugin.gestures.OnMapClickListener
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import com.mapbox.maps.plugin.locationcomponent.LocationComponentPlugin
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.locationcomponent.location2
import com.mapbox.maps.viewannotation.ViewAnnotationManager
import com.mapbox.maps.viewannotation.viewAnnotationOptions


private lateinit var mapboxMap: MapboxMap
lateinit var  floatingActionButton: FloatingActionButton

class Map_box_Fragment : Fragment()  {
    private  val args :Map_box_FragmentArgs by navArgs();
    @MapboxExperimental
    private lateinit var viewAnnotationManager: ViewAnnotationManager
    var mapView: MapView? = null


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMapBoxBinding.inflate(layoutInflater)

        val rootView = inflater.inflate(R.layout.fragment_map_box_, container, false)

        //mapView = rootView.findViewById(R.id.mapView)
        /*
        val binding = FragmentMapBoxBinding.inflate(layoutInflater)

        viewAnnotationManager = binding.mapView.viewAnnotationManager
floatingActionButton = rootView.findViewById(R.id.fab_style_toggle)
        mapboxMap = binding.mapView.getMapboxMap().apply {
            loadStyleUri(Style.MAPBOX_STREETS) {
                addOnMapClickListener(this@Map_box_Fragment)
                //binding.fabStyleToggle.setOnClickListener {
                floatingActionButton.setOnClickListener {

                    when (getStyle()?.styleURI) {
                        Style.MAPBOX_STREETS -> loadStyleUri(Style.SATELLITE_STREETS)
                        Style.SATELLITE_STREETS -> loadStyleUri(Style.MAPBOX_STREETS)
                    }
                }
                Toast.makeText(context, STARTUP_TEXT, Toast.LENGTH_LONG).show()
            }
        }

*/
        //mapView?.getMapboxMap()?.loadStyleUri(Style.MAPBOX_STREETS)
        //val viewAnnotationManager = mapView?.viewAnnotationManager
/*
        mapView?.getMapboxMap()?.loadStyleUri(
            Style.MAPBOX_STREETS,

                // After the style is loaded, initialize the Location component.
            object : Style.OnStyleLoaded {
                override fun onStyleLoaded(style: Style) {
                    mapView?.location?.updateSettings {
                        addAnnotationToMap()
                        val center = mapboxMap.cameraState.center
                        // Add the view annotation at the center point
                        addViewAnnotation(center)



                    }
                }
            }
        )
*/


        mapView = rootView.findViewById(R.id.mapView)
        mapView?.getMapboxMap()?.loadStyleUri(
            Style.MAPBOX_STREETS,
            object : Style.OnStyleLoaded {
                override fun onStyleLoaded(style: Style) {
                    addAnnotationToMap()
                }
            }
        )

/*
        viewAnnotationManager = binding.mapView.viewAnnotationManager
        mapboxMap = binding.mapView.getMapboxMap().apply {
            // Load a map style
            loadStyleUri(Style.MAPBOX_STREETS) {
                // Get the center point of the map
                val center = mapboxMap.cameraState.center
                // Add the view annotation at the center point
                //addViewAnnotation(center)
                addAnnotationToMap()
            }
        }
*/


        /*
        mapView?.location?.locationPuck = LocationPuck2D(
            topImage = AppCompatResources.getDrawable(
                context!!,
                com.mapbox.maps.plugin.locationcomponent.R.drawable.mapbox_user_icon
            ),
            bearingImage = AppCompatResources.getDrawable(
                context!!,
                com.mapbox.maps.plugin.locationcomponent.R.drawable.mapbox_user_bearing_icon
            ),
            shadowImage = AppCompatResources.getDrawable(
                context!!,
                com.mapbox.maps.plugin.locationcomponent.R.drawable.mapbox_user_stroke_icon
            ),
            scaleExpression = interpolate {
                linear()
                zoom()
                stop {
                    literal(0.0)
                    literal(0.6)
                }
                stop {
                    literal(20.0)
                    literal(1.0)
                }
            }.toJson()
        )
        mapView?.location2?.puckBearingSource = PuckBearingSource.HEADING
        mapView?.location2?.puckBearingSource = PuckBearingSource.COURSE
*/

        return rootView
    }

    private fun addAnnotationToMap() {
// Create an instance of the Annotation API and get the PointAnnotationManager.
        bitmapFromDrawableRes(
            context!!,
            R.drawable.red_marker
        )?.let {

            val annotationApi = mapView?.annotations
            val pointAnnotationManager = annotationApi?.createPointAnnotationManager(mapView!!)
// Set options for the resulting symbol layer.
            val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
// Define a geographic coordinate.

                .withPoint(Point.fromLngLat(args.lat.toDouble(),args.long.toDouble() ))
// Specify the bitmap you assigned to the point annotation
// The bitmap will be added to map style automatically.
                .withIconImage(it)
                .withTextField(args.name)
                //.withTextColor("@Color/dgreen_2")
                //.withDraggable(true)

// Add the resulting pointAnnotation to the map.
            pointAnnotationManager?.create(pointAnnotationOptions)
            println("*********************************************************")

            println(args.lat.toDouble())


        }
    }
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

/*
    override fun onMapClick(point: Point): Boolean {
        addViewAnnotation(point)
        return true
    }

    @SuppressLint("SetTextI18n")
    private fun addViewAnnotation(point: Point) {
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

 */
}