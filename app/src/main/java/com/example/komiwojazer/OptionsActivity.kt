package com.example.komiwojazer

import android.content.Intent
import android.location.GnssAntennaInfo
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random


class OptionsActivity : AppCompatActivity() {
    lateinit var btnAdd:Button;
    lateinit var btnSave:Button;
    lateinit var btnDelete:Button;
    lateinit var editTextCity:EditText;
    lateinit var editTextDistance:EditText;
    lateinit var recyclerview: RecyclerView;
    lateinit var cities:MutableList<CitiesViewModel>;
    val SIZE = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        btnAdd = findViewById(R.id.btnAdd)
        btnSave = findViewById(R.id.btnSave)

        recyclerview = findViewById<RecyclerView>(R.id.recyclerView);
        editTextCity = findViewById(R.id.editTextCity)
        editTextDistance = findViewById(R.id.editTextDistance)

        btnSave.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }

        btnAdd.setOnClickListener {
            if(cities.size < 8){
                cities.add(CitiesViewModel(editTextCity.text.toString(),
                    editTextDistance.text.toString().toInt())
                )
                recyclerview.adapter?.notifyItemInserted(cities.size)
            }
        }

        cities = mutableListOf()
        generateRandomCities()
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

    private fun generateRandomCities(){
        for(i in 1..SIZE){
            cities.add(CitiesViewModel("City"+i.toString(),Random.nextInt(1,10)))
        }
        recyclerview.adapter?.notifyDataSetChanged()
    }
}