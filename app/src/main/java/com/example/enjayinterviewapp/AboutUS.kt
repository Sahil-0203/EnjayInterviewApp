package com.example.enjayinterviewapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class AboutUS : AppCompatActivity() {
        lateinit var webView: WebView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        webView=findViewById(R.id.webview)

        webView.loadUrl("https://www.enjayworld.com/about/?")
        webView.settings.javaScriptEnabled = true



    }

    override fun onBackPressed() {
        startActivity(Intent(applicationContext,HomeActivity::class.java))
        finish()

    }
}