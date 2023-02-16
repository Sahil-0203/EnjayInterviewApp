package com.example.enjayinterviewapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PythonQuiz : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_python_quiz)

        val start=findViewById<Button>(R.id.quizStart)

        start.setOnClickListener {

            intent= Intent(this@PythonQuiz,PythonQuestion::class.java)
            startActivity(intent)

        }
    }
}