package com.mosnanaja.mapproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import java.lang.NullPointerException


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, View.OnClickListener , OnInfoWindowClickListener{
    override fun onInfoWindowClick(marker: Marker) {
//        val intent = Intent(this, Adapter::class.java)
//        val title = marker.title
//        tv1.text = "fah".toString()
//        if (!title.contains("Marker in Sydney")) { // if bus stop
//            intent.putExtra("markertitle", title)
//            startActivity(intent)
//        } else {
//            // whatever you need to do for schools
//        }
    }

    override fun onClick(v: View?) {
        when (v) {
//            btnJJ -> {
//                moveToPlaceTarget(13.805194, 100.551894, "เจเจกรีน"); Toast.makeText(this, "put move to JJ Here!!!", Toast.LENGTH_LONG).show()
//            }
//            btnSJ -> {
//                moveToPlaceTarget(13.809275, 100.559083, "ตึกนี้แหละ"); Toast.makeText(this, "put move to SJ Here!!!", Toast.LENGTH_LONG).show()
//            }
//            btnSent -> {
//                moveToPlaceTarget(13.815813, 100.560972, "เซนลาด"); Toast.makeText(this, "put move to Sent Here!!!", Toast.LENGTH_LONG).show()
//            }
            btn_goTo_youLocation -> {
                gps = GPSTracker(this@MapsActivity)
//
//                if (gps.canGetLocation()) {
//
//                    val latitude = gps.getLatitude()
//                    val longitude = gps.getLongitude()
//
//                    //txtLocation.setText("ตำแหน่งของคุณคือ - \nLat: $latitude\nLong: $longitude")
//                    moveToPlaceTarget(latitude, longitude, "เซนลาด"); Toast.makeText(this, "put move to Sent Here!!!", Toast.LENGTH_LONG).show()
//                } else {
//                    //txtLocation.setText("อุปกรณ์์ของคุณ ปิด GPS")
//                }

//                moveToPlaceTarget(13.815813, 100.560972, "เซนลาด"); Toast.makeText(this, "put move to Sent Here!!!", Toast.LENGTH_LONG).show()
            }
            btn_goTo_maps -> {
                val lat = 13.815813
                val long = 100.560972
                val namePlace = "CentralPlaza Ladprao"
//                val gmmIntentUri = Uri.parse("google.navigation:q=" + lat.toString() + "," + long.toString())
                val gmmIntentUri = Uri.parse("geo:" + lat.toString() + "," + long.toString() + "?q=" + namePlace)
//                val gmmIntentUri = Uri.parse("google.navigation:q=" + namePlace+"thailand")
//                val gmmIntentUri = Uri.parse("google.navigation:q=" + lat + "," + long + "," + namePlace)
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.`package` = "com.google.android.apps.maps"
                startApp(mapIntent)
//            startActivity(mapIntent);}
                Toast.makeText(this, "Open Google Maps", Toast.LENGTH_SHORT).show()
            }
        }
    }

    inner class GPSTracker(mapsActivity: MapsActivity) : android.location.LocationListener {
        override fun onLocationChanged(location: android.location.Location?) {
            for (i in 0 until plaseList!!.size) {
                val distance = distance(location!!.getLatitude(), location.getLongitude(), plaseList!!.get(i).getLatitude()!!, plaseList.get(i).getLongitude()!!)
//                if (distance <= plaseList.get(i).getDistance()!!) {
                    if (plaseList.get(i).getName().toString() == inPlase) {
                        //plaseList.remove(i);
                        break
                    } else {
                        inPlase = plaseList.get(i).getName().toString()
//                        showNotification(location, distance, plaseList.get(i).getName())
                    }
//                }
            }
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

        }

        override fun onProviderEnabled(provider: String?) {

        }

        override fun onProviderDisabled(provider: String?) {

        }

        private fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
            val theta = lon1 - lon2
            var dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + (Math.cos(deg2rad(lat1))
                    * Math.cos(deg2rad(lat2))
                    * Math.cos(deg2rad(theta)))
            dist = Math.acos(dist)
            dist = rad2deg(dist)
            dist = dist * 60.0 * 1.1515
            return dist
        }

        private fun deg2rad(deg: Double): Double {
            return deg * Math.PI / 180.0
        }

        private fun rad2deg(rad: Double): Double {
            return rad * 180.0 / Math.PI
        }
    }

    private fun moveToPlaceTarget(Lat: Double, Lng: Double, namePlace: String) {
        val place = LatLng(Lat, Lng)
        val cameraPosition = CameraPosition.Builder()
                .target(place)      // Sets the center of the map to Mountain View
                .zoom(20f)      // Sets the zoom
                .build()
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }


    private lateinit var mMap: GoogleMap
    //    var gps: GPSTracker? = null
    var gps: GPSTracker? = null
    var txtLocation: TextView? = null
    private val plaseList: List<Location>? = null
    private var inPlase = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val recycleView = findViewById<TextView>(R.id.recycleView) as RecyclerView
        recycleView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)



        initOnClick()

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    private fun initOnClick() {
//        btnJJ.setOnClickListener(this)
//        btnSJ.setOnClickListener(this)
//        btnSent.setOnClickListener(this)
//        btn_goTo_maps.setOnClickListener(this)
        btn_goTo_youLocation.setOnClickListener(this)
        btn_goTo_maps.setOnClickListener(this)
    }

    private fun addLocation(): ArrayList<Location> {
        val location = ArrayList<Location>()
        location.add(Location("JJ GREEN", "testLocation",13.805194,100.551894))
        location.add(Location("SJ", "testLocation2",13.809275,100.559083))
        location.add(Location("CentralPlaza Ladprao", "testLocation3",13.815813, 100.560972))
        location.add(Location("CentralPlaza Ladprao2", "testLocation4",13.815813, 100.560972))
        location.add(Location("CentralPlaza Ladprao3", "testLocation5",13.815813, 100.560972))
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
        val adapter = Adapter(addLocation(),mMap)

        recycleView.adapter = adapter
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        val sj = LatLng(13.809275, 100.559083)

        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

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


