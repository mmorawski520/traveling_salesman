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
    lateinit var btnAdd:Button;
    lateinit var btnSave:Button;
    lateinit var btnGenerate:Button;
    lateinit var editTextCity:EditText;
    lateinit var editTextDistance:EditText;
    lateinit var recyclerview: RecyclerView;
    lateinit var cities:MutableList<CitiesViewModel>;
    val SIZE = 8
    val tsp2 = Array(SIZE) { IntArray(SIZE) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        btnAdd = findViewById(R.id.btnAdd)
        btnSave = findViewById(R.id.btnSave)
        btnGenerate = findViewById(R.id.btnGenerate)

        recyclerview = findViewById(R.id.recyclerView);
        editTextCity = findViewById(R.id.editTextCity)
        editTextDistance = findViewById(R.id.editTextDistance)
        val singleToneClass: citiesSingletone =
            com.example.komiwojazer.citiesSingletone.instance
        btnSave.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            singleToneClass.value = cities
            this.startActivity(intent)
        }

        btnAdd.setOnClickListener {
            if(cities.size < 8){
                if(cities.size>1)
                cities.add(CitiesViewModel(editTextCity.text.toString(),
                    editTextDistance.text.toString().toInt(),cities.last().city)
                )
                else
                    cities.add(CitiesViewModel(editTextCity.text.toString(),
                        editTextDistance.text.toString().toInt(),"")
                    )
                recyclerview.adapter?.notifyItemInserted(cities.size)
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
            var curCityIndex =0
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

        cities = mutableListOf()
        cities=singleToneClass.value
        recyclerview.adapter?.notifyDataSetChanged()
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@OptionsActivity)
            adapter = CityAdapter(cities)
        }

        val swipeToDeleteCallback = object:SwipeToDeleteCallback(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                cities.removeAt(pos)
                recyclerview.adapter?.notifyItemRemoved(pos)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerview)
    }
}