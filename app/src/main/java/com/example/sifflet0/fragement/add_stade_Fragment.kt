package com.example.sifflet0.fragement

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.fragment.findNavController
import com.example.sifflet0.R
import com.example.sifflet0.api.RetrofiteInstance
import com.example.sifflet0.databinding.AnnotationTextBinding
import com.example.sifflet0.databinding.FragmentAddStadeBinding
import com.example.sifflet0.models.User
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.gestures.OnMapClickListener
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import com.mapbox.maps.viewannotation.ViewAnnotationManager
import com.mapbox.maps.viewannotation.viewAnnotationOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class add_stade_Fragment : Fragment() , OnMapClickListener {



    @MapboxExperimental
    private lateinit var viewAnnotationManager1: ViewAnnotationManager
    var mapView1: MapView? = null
     var mapboxMap1: MapboxMap? = null
    lateinit var  floatingActionButton1:FloatingActionButton
    lateinit var  builder: AlertDialog.Builder
     var  long: Float ?=null
     var  lat: Float ?=null


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        //setContentView(binding.root)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val  rootView = inflater.inflate(R.layout.fragment_add_stade_, container, false)

        val binding = FragmentAddStadeBinding.inflate(layoutInflater)
        floatingActionButton1 = rootView.findViewById(R.id.fab_style_toggle1)

        viewAnnotationManager1 = binding.mapView1.viewAnnotationManager
        mapView1 = rootView.findViewById(R.id.mapView1)

        //mapboxMap1?.apply {
        mapView1?.getMapboxMap()?.apply {
            loadStyleUri(Style.MAPBOX_STREETS) {
                addOnMapClickListener(this@add_stade_Fragment)
                //binding.fabStyleToggle1.setOnClickListener {
                    floatingActionButton1.setOnClickListener {
                        println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr")

                    when (getStyle()?.styleURI) {
                        Style.MAPBOX_STREETS -> loadStyleUri(Style.SATELLITE_STREETS)
                        Style.SATELLITE_STREETS -> loadStyleUri(Style.MAPBOX_STREETS)
                    }
                }
                Toast.makeText(context, STARTUP_TEXT, Toast.LENGTH_LONG).show()
            }
        }



        return rootView
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
        mapView1?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView1?.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView1?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView1?.onDestroy()
    }



    override fun onMapClick(point: Point): Boolean {
        addViewAnnotation(point)
        return true
    }





    @SuppressLint("SetTextI18n")
    private fun addViewAnnotation(point: Point) {
        val viewAnnotation = viewAnnotationManager1.addViewAnnotation(
            resId = R.layout.annotation_text,
            options = viewAnnotationOptions {
                geometry(point)
                allowOverlap(true)
            }

        )
        addAnnotationToMap(point.latitude(),point.longitude())
    }

    fun basicAlert(lat: Double,long : Double){

        val builder = AlertDialog.Builder(context)

        with(builder)
        {

            val positiveButtonClick = { dialog: DialogInterface, which: Int ->
                val action = add_stade_FragmentDirections.actionAddStadeFragmentToAjouterStadeFragment(
                    lat.toFloat(),
                    long.toFloat()
                )
                findNavController().navigate(action)
            }
            val negativeButtonClick = { dialog: DialogInterface, which: Int ->
                Toast.makeText(context,
                    android.R.string.no, Toast.LENGTH_SHORT).show()
            }

            setTitle("Choix du cordonne")
            setMessage("We have a message")
            setPositiveButton("Oui", DialogInterface.OnClickListener(function = positiveButtonClick))
            setNegativeButton(android.R.string.no, negativeButtonClick)
            //setNeutralButton("Maybe", neutralButtonClick)
            show()
        }


    }


    private companion object {
        const val SELECTED_ADD_COEF_PX = 25
        const val STARTUP_TEXT = "Click on a map to add a view annotation."
    }
    private fun addAnnotationToMap(lat: Double,long : Double) {
// Create an instance of the Annotation API and get the PointAnnotationManager.
        bitmapFromDrawableRes(
            context!!,
            R.drawable.red_marker
        )?.let {

            val annotationApi = mapView1?.annotations
            val pointAnnotationManager = annotationApi?.createPointAnnotationManager(mapView1!!)
// Set options for the resulting symbol layer.
            val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
// Define a geographic coordinate.

                .withPoint(Point.fromLngLat(long,lat ))
// Specify the bitmap you assigned to the point annotation
// The bitmap will be added to map style automatically.
                .withIconImage(it)
                //.withTextField(args.name)
            //.withTextColor("@Color/dgreen_2")
            //.withDraggable(true)

// Add the resulting pointAnnotation to the map.
            pointAnnotationManager?.create(pointAnnotationOptions)
            basicAlert(lat,long)

        }
    }


}