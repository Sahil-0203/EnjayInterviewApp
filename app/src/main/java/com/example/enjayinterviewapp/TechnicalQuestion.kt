package com.example.enjayinterviewapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class TechnicalQuestion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_technical_question)

        val android=findViewById<CardView>(R.id.android)
        val ios=findViewById<CardView>(R.id.ios)
        val web=findViewById<CardView>(R.id.web)

        android.setOnClickListener {
            startActivity(Intent(this@TechnicalQuestion,Android_technical::class.java))
        }

        ios.setOnClickListener {
            startActivity(Intent(this@TechnicalQuestion,Ios_technical::class.java))
        }

        web.setOnClickListener {
            startActivity(Intent(this@TechnicalQuestion,Web_technical::class.java))
        }
    }
}