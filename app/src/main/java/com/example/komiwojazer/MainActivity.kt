package com.example.komiwojazer

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random
import kotlin.reflect.typeOf


class MainActivity : AppCompatActivity() {
    lateinit var btnGenerateCities: ImageButton;
    lateinit var btnOptions: ImageButton;
    lateinit var btnExport: ImageButton;
    lateinit var btnCalculate: Button;
    lateinit var textViewResult: TextView;
    lateinit var recyclerView: RecyclerView;
    lateinit var cities: MutableList<CitiesViewModel>;
    lateinit var cityNames: MutableList<String>;
    val tsp2 = Array(8) { IntArray(8) }
    val SIZE = 8


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGenerateCities = findViewById(R.id.btnGenerateCities);
        btnOptions = findViewById(R.id.btnSettings)
        btnExport = findViewById(R.id.btnExport)
        btnCalculate = findViewById(R.id.btnCalculate)
        textViewResult = findViewById(R.id.textViewResult)
        recyclerView = findViewById(R.id.recyclerView)

        cities = mutableListOf()
        cityNames = mutableListOf()
        val singleToneClass: citiesSingletone =
            com.example.komiwojazer.citiesSingletone.instance

        try {


            cities = singleToneClass.value
            var curCityIndex = 0;
            for (i in 0..SIZE - 1) {
                for (j in 0..SIZE - 1) {
                    if (j == curCityIndex) {
                        tsp2[i][j] = 0
                        curCityIndex++
                    } else {
                        tsp2[i][j] = cities[i].distance
                    }
                }
            }
            Toast.makeText(this, "Cities had been loaded", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
        btnGenerateCities.setOnClickListener {
            cities = mutableListOf()
            for (i in 1..SIZE) {
                cities.add(
                    CitiesViewModel(
                        "City" + i.toString(),
                        Random.nextInt(1, 10),
                        "City" + (i - 1).toString()
                    )
                )
            }
            var curCityIndex = 0;
            for (i in 0..SIZE - 1) {
                for (j in 0..SIZE - 1) {
                    if (j == curCityIndex) {
                        tsp2[i][j] = 0
                        curCityIndex++
                    } else {
                        tsp2[i][j] = Random.nextInt(1, 100)
                    }
                }
            }


            Toast.makeText(this, "Cities had been generated", Toast.LENGTH_SHORT).show()
        }

        btnOptions.setOnClickListener {
            val intent = Intent(this, OptionsActivity::class.java)
            singleToneClass.value = cities
            this.startActivity(intent)
        }

        btnExport.setOnClickListener {
            MediaStore.Images.Media.insertImage(
                getContentResolver(), takeScreenshot(),
                getcurrentDate() + Random.hashCode(), "description"
            );
            Toast.makeText(this, "Image had been saved in your gallery", Toast.LENGTH_SHORT).show()
        }

        btnCalculate.setOnClickListener {
            if (cities.size != 0) {
                var a = Algorithm(tsp2)
                a.solve()
                var tour = a.getTour()

                cityNames.clear()
                for (i in 0..SIZE) {
                    cityNames.add(i, cities[tour[i]].city)
                }

                val adapter = CityResultAdapter(cityNames)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = adapter
                recyclerView.adapter?.notifyDataSetChanged()
                textViewResult.text = "Cost of the trip " + a.getCost.toString()
            }
        }
    }

    fun getcurrentDate(): String? {
        val c: Date = Calendar.getInstance().getTime()
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
        return simpleDateFormat.format(c)
    }

    fun takeScreenshot(): Bitmap {
        val rootView: View = findViewById<View>(android.R.id.content).rootView
        rootView.setDrawingCacheEnabled(true)
        return rootView.getDrawingCache()
    }
}