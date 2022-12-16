package com.example.komiwojazer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random


class OptionsActivity : AppCompatActivity() {
    private lateinit var btnAdd: Button
    private lateinit var btnSave: Button
    private lateinit var btnGenerate: Button
    private lateinit var editTextCity: EditText
    private lateinit var editTextDistance: EditText
    lateinit var recyclerview: RecyclerView

    private lateinit var cities: MutableList<CitiesViewModel>
    private val SIZE = 8
    private val tsp2 = Array(SIZE) { IntArray(SIZE) }

    var isEdited = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        initLayoutObjects()
        cities = mutableListOf()

        val singleToneClass: citiesSingletone = citiesSingletone.instance

        btnSave.setOnClickListener {
            singleToneClass.value = cities
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }

        btnAdd.setOnClickListener {
            if (cities.size < SIZE) {
                if (cities.size > 1)
                    cities.add(
                        CitiesViewModel(
                            editTextCity.text.toString(),
                            editTextDistance.text.toString().toInt(), cities.last().city
                        )
                    )
                else
                    cities.add(
                        CitiesViewModel(
                            editTextCity.text.toString(),
                            editTextDistance.text.toString().toInt(), ""
                        )
                    )
                recyclerview.adapter?.notifyItemInserted(cities.size)
            } else {
                Toast.makeText(this, "You can't add more cities", Toast.LENGTH_SHORT).show()
            }
        }

        btnGenerate.setOnClickListener {
            cities.clear()
            for (i in 1..SIZE) {
                cities.add(
                    CitiesViewModel(
                        "City" + i.toString(),
                        Random.nextInt(1, 10),
                        "City" + (i - 1).toString()
                    )
                )
            }
            var curCityIndex = 0
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

            recyclerview.adapter?.notifyDataSetChanged()
            singleToneClass.value = cities
            Toast.makeText(this, "Cities had been generated", Toast.LENGTH_SHORT).show()
        }

        cities.clear()
        cities = singleToneClass.value
        recyclerview.adapter?.notifyDataSetChanged()
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@OptionsActivity)
            adapter = CityAdapter(cities)
        }

        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                cities.removeAt(pos)
                recyclerview.adapter?.notifyItemRemoved(pos)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerview)
    }

    fun initLayoutObjects() {
        btnAdd = findViewById(R.id.btnAdd)
        btnSave = findViewById(R.id.btnSave)
        btnGenerate = findViewById(R.id.btnGenerate)

        recyclerview = findViewById(R.id.recyclerView)
        editTextCity = findViewById(R.id.editTextCity)
        editTextDistance = findViewById(R.id.editTextDistance)
    }
}