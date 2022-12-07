package com.example.komiwojazer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var btnGenerateCities: ImageButton;
    lateinit var btnOptions: ImageButton;
    lateinit var btnCalculate: Button;
    lateinit var cities:Array<CitiesViewModel>;
    val SIZE = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGenerateCities = findViewById(R.id.btnGenerateCities);
        btnOptions = findViewById(R.id.btnSettings)
        btnCalculate = findViewById(R.id.btnCalculate)

        btnGenerateCities.setOnClickListener {
            for(i in 0..SIZE -1){
                cities[i] = CitiesViewModel("City"+i.toString(), Random.nextInt(1,10))
        }
        }

        btnOptions.setOnClickListener {
            val intent = Intent(this, OptionsActivity::class.java)
            this.startActivity(intent)
        }

        btnCalculate.setOnClickListener {

        }
    }
}