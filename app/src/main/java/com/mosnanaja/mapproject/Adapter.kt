package com.mosnanaja.mapproject

import android.view.LayoutInflater
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

/**
 * Created by Noob Fahh on 10/11/2017.
 */
class Adapter (val locationList:ArrayList<Location>):RecyclerView.Adapter<Adapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.list_layout, parent ,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val location: Location = locationList[position]
        holder?.textViewName?.text = location.name
        holder?.textViewDescription?.text = location.descriptor

        holder?.itemView?.setOnClickListener {
            moveToPlaceTarget(13.805194, 100.551894, "เจเจกรีน")
            println("test")
        }
    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById<TextView>(R.id.textViewName) as TextView
        val textViewDescription = itemView.findViewById<TextView>(R.id.textViewDescription) as TextView
    }
    private fun moveToPlaceTarget(Lat: Double, Lng: Double, namePlace: String) {
        val place = LatLng(Lat, Lng)
        val cameraPosition = CameraPosition.Builder()
                .target(place)      // Sets the center of the map to Mountain View
                .zoom(20f)      // Sets the zoom
                .build()
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
  //  private lateinit var mMap: GoogleMap

}
