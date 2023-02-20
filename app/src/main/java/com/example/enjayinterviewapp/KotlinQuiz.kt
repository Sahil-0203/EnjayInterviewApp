package com.example.enjayinterviewapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class KotlinQuiz : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_quiz)
        val start=findViewById<Button>(R.id.kotlinquizstart)

        start.setOnClickListener {
            intent= Intent(applicationContext,KotlinQuestion::class.java)
            startActivity(intent)

        }
    }
}