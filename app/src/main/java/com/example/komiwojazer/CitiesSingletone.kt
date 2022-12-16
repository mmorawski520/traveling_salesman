package com.example.komiwojazer

class citiesSingletone private constructor() {
    var value: MutableList<CitiesViewModel> = mutableListOf()

    companion object {
        val instance = citiesSingletone()
    }
}