package com.example.enjayinterviewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Handler().postDelayed(
            {

                auth=FirebaseAuth.getInstance()

                if (auth.currentUser != null) {

                    startActivity(Intent(applicationContext,HomeActivity::class.java))
                    finish()
                }
                else {

                    startActivity(Intent(this@MainActivity, login::class.java))
                    finish()
                }
            },1000)


    }
}