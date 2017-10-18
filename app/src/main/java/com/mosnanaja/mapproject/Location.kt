package com.mosnanaja.mapproject


/**
 * Created by Noob Fahh on 10/11/2017.
 */
data class Location(var name: String, val description: String, var lat: Double, var long: Double,var date: String,var time: String) {
  
    fun getLatitude(): Double? {
        return lat
    }

    fun setLatitude(latitude: Double?) {
        this.lat = latitude!!
    }

    fun getLongitude(): Double? {
        return long
    }

    fun setLongitude(longitude: Double?) {
        this.long = longitude!!
    }


//    fun getDistance(): Double? {
//        return distance
//    }
//
//    fun setDistance(distance: Double?) {
//        this.distance = distance!!
//    }
    fun getName(){

    }
    fun setName(){

    }

//    fun getName(): String {
//        return name
//    }
//
//    fun setName(name: String) {
//        this.name = name
//    }
}