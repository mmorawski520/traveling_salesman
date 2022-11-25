package com.example.komiwojazer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    lateinit var btnSave: ImageButton;
    lateinit var btnOptions: ImageButton;
    lateinit var btnCalculate: Button;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave = findViewById(R.id.btnSave);
        btnOptions = findViewById(R.id.btnSettings)
        btnCalculate = findViewById(R.id.btnCalculate)

        btnSave.setOnClickListener {

        }

        btnOptions.setOnClickListener {
            val intent = Intent(this, OptionsActivity::class.java)
            this.startActivity(intent)
        }

        btnCalculate.setOnClickListener {

        }
    }
}