package com.example.komiwojazer

class City {
    private var city: String? = null
    private var distance = 0

    fun City(city: String?, imgId: Int) {
        this.city = city
        this.distance = distance
    }

    fun getCity(): String? {
        return city
    }

    fun setCity(description: String?) {
        this.city = description
    }

    fun setDistance(city:String?){
        this.distance = distance
    }

    fun getDistance():Number?{
        return this.distance
    }
}