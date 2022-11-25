package com.example.komiwojazer

import android.content.Intent
import android.location.GnssAntennaInfo
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random


class OptionsActivity : AppCompatActivity() {
    lateinit var btnAdd:Button;
    lateinit var btnSave:Button;
    lateinit var btnDelete:Button;
    lateinit var editTextCity:EditText;
    lateinit var editTextDistance:EditText;
    lateinit var recyclerview: RecyclerView;
    val data = ArrayList<CitiesViewModel>()
    lateinit var cities:Array<City>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        btnAdd = findViewById(R.id.btnAdd)
        btnSave = findViewById(R.id.btnSave)
        btnDelete = findViewById(R.id.btnDelete)

        recyclerview = findViewById<RecyclerView>(R.id.recyclerView);
        editTextCity = findViewById(R.id.editTextCity)
        editTextDistance = findViewById(R.id.editTextDistance)

        btnSave.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }

        btnAdd.setOnClickListener {

        }
        btnDelete.setOnClickListener {

        }

        generateRandomCities()
    }

    private fun generateRandomCities(){

        recyclerview.layoutManager = LinearLayoutManager(this)


        for (i in 1..16) {
            data.add(CitiesViewModel("City " + i.toString(), Random.nextInt(1, 10)))

        }

        val adapter = CustomAdapter(data)
        recyclerview.adapter = adapter
    }
}