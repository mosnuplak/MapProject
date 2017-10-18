package com.mosnanaja.mapproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import java.lang.NullPointerException
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.BitmapFactory
import android.view.Gravity
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import android.support.v7.widget.SnapHelper








class MapsActivity : AppCompatActivity(), OnMapReadyCallback  , OnInfoWindowClickListener{


    private lateinit var eventLocation:ArrayList<LocationEvent>

    override fun onInfoWindowClick(marker: Marker) {
      //  Toast.makeText(this,"test",Toast.LENGTH_SHORT).show()
        println(marker.title)
      //  val intent = Intent(this, Adapter::class.java)
        val title = marker.title
        val snapHelperTop = GravitySnapHelper(Gravity.TOP)
        for(i in 0 until eventLocation.size){
            if(title.contains(eventLocation[i].name)){
                snapHelperTop.attachToRecyclerView(recycleView)
                recycleView.smoothScrollToPosition(i)

            }
        }

    }


//    override fun onClick(v: View?) {
//        when (v) {
////            btnJJ -> {
////                moveToPlaceTarget(13.805194, 100.551894, "เจเจกรีน"); Toast.makeText(this, "put move to JJ Here!!!", Toast.LENGTH_LONG).show()
////            }
////            btnSJ -> {
////                moveToPlaceTarget(13.809275, 100.559083, "ตึกนี้แหละ"); Toast.makeText(this, "put move to SJ Here!!!", Toast.LENGTH_LONG).show()
////            }
////            btnSent -> {
////                moveToPlaceTarget(13.815813, 100.560972, "เซนลาด"); Toast.makeText(this, "put move to Sent Here!!!", Toast.LENGTH_LONG).show()
////            }
//            btn_goTo_youLocation -> {
//                gps = GPSTracker(this@MapsActivity)
////
////                if (gps.canGetLocation()) {
////
////                    val latitude = gps.getLatitude()
////                    val longitude = gps.getLongitude()
////
////                    //txtLocation.setText("ตำแหน่งของคุณคือ - \nLat: $latitude\nLong: $longitude")
////                    moveToPlaceTarget(latitude, longitude, "เซนลาด"); Toast.makeText(this, "put move to Sent Here!!!", Toast.LENGTH_LONG).show()
////                } else {
////                    //txtLocation.setText("อุปกรณ์์ของคุณ ปิด GPS")
////                }
//
////                moveToPlaceTarget(13.815813, 100.560972, "เซนลาด"); Toast.makeText(this, "put move to Sent Here!!!", Toast.LENGTH_LONG).show()
//            }
//            btn_goTo_maps -> {
//                val lat = 13.815813
//                val long = 100.560972
//                val namePlace = "CentralPlaza Ladprao"
////                val gmmIntentUri = Uri.parse("google.navigation:q=" + lat.toString() + "," + long.toString())
//                val gmmIntentUri = Uri.parse("geo:" + lat.toString() + "," + long.toString() + "?q=" + namePlace)
////                val gmmIntentUri = Uri.parse("google.navigation:q=" + namePlace+"thailand")
////                val gmmIntentUri = Uri.parse("google.navigation:q=" + lat + "," + long + "," + namePlace)
//                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
//                mapIntent.`package` = "com.google.android.apps.maps"
//                startApp(mapIntent)
////            startActivity(mapIntent);}
//                Toast.makeText(this, "Open Google Maps", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }


    private lateinit var mMap: GoogleMap
    //    var gps: GPSTracker? = null

    var txtLocation: TextView? = null
    private val plaseList: List<Location>? = null
    private var inPlase = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val recycleView = findViewById<TextView>(R.id.recycleView) as RecyclerView
        recycleView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)





        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }





    private fun addLocation(): ArrayList<LocationEvent> {
        val location = ArrayList<LocationEvent>()
        location.add(LocationEvent("JJ GREEN", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout."
                ,13.805209, 100.551896,"Sep 16, 2018","9:00PM"))
        location.add(LocationEvent("SJ", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout."
                ,13.809275,100.559083,"Sep 16, 2018","9:00PM"))
        location.add(LocationEvent("CentralPlaza Ladprao", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout."
                ,13.815831, 100.560974,"Sep 16, 2018","9:00PM"))
        location.add(LocationEvent("Bangsaen beach", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout."
                ,13.298562, 100.902067,"Sep 16, 2018","9:00PM"))
        location.add(LocationEvent("MRTChatuchak", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout."
                ,13.803908, 100.554091,"Sep 16, 2018","9:00PM"))
        return location
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        eventLocation = addLocation()
        val adapter = Adapter(eventLocation,mMap)
        mMap.uiSettings.isMapToolbarEnabled = false
        recycleView.adapter = adapter
        // Add a marker in Sydney and move the camera

        for(i in 0 until eventLocation.size){
            val place = LatLng(eventLocation[i].lat,eventLocation[i].long)
            mMap.addMarker(MarkerOptions()
                    .position(place)
                    .title(eventLocation[i].name)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin))
            )
        }

        val MRTChatuchak  = LatLng(13.803908, 100.554091)
        //val event = android.location.Locationocation()
        //val distance = jatujuk.distanceTo(jatujuk) / 1000
        mMap.addMarker(MarkerOptions().position(MRTChatuchak).title("MRTChatuchak"))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MRTChatuchak,15f))
        mMap.setOnInfoWindowClickListener(this)

    }

    private fun startApp(mapIntent: Intent) {
        try {
//            var intent = packageManager.getLaunchIntentForPackage(mapIntent.toString())
//            intent!!.addCategory(Intent.CATEGORY_LAUNCHER)
            startActivity(mapIntent)
        } catch (e: NullPointerException) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("market://details?id=" + mapIntent)
            startActivity(intent)
        }
    }

}


