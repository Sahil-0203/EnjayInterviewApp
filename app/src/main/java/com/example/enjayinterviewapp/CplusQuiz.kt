package com.example.enjayinterviewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class CplusQuiz : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cplus_quiz)
        val start=findViewById<Button>(R.id.cplusquizstart)

        start.setOnClickListener {
            intent= Intent(applicationContext, CplusQuestion::class.java)
            startActivity(intent)
            finish()

        }
    }
}