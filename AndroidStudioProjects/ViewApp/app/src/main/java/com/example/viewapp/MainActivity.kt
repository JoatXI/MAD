package com.example.viewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val butn = findViewById<Button>(R.id.btn1)
        butn.setOnClickListener {
            val et = findViewById<EditText>(R.id.et1) // * et: Edit Text variable that takes in the user input
            val name = et.text.toString()
            val tv = findViewById<TextView>(R.id.tv1) // * tv: Text View variable that displays the text to the user

            tv.text = "Hello $name"
        }
    }
}