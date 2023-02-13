package com.example.enjayinterviewapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PhpQuiz : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_php_quiz)

        val start=findViewById<Button>(R.id.phpquizstart)

        start.setOnClickListener {
            intent= Intent(applicationContext,PhpQuestion::class.java)
            startActivity(intent)
            finish()

        }
    }
}