package com.example.enjayinterviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Ios_technical : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ios_technical)

        val recyclerView=findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView.layoutManager=LinearLayoutManager(this)

        val data=ArrayList<CommomModelClass>()

        data.add(CommomModelClass("1. What is ARC?",R.string.ios1))
        data.add(CommomModelClass("2. Define Bundle ID?",R.string.ios2))
        data.add(CommomModelClass("3. Name some important data types found in objective-C?",R.string.ios3))
        data.add(CommomModelClass("4. Define Cocoa/Cocoa touch?",R.string.ios4))
        data.add(CommomModelClass("5. What are the methods to achieve concurrency?",R.string.ios5))
        data.add(CommomModelClass("6. When is an app said to be not running the state?",R.string.ios6))


        var adapter=CommomAdapterClass(data)
        recyclerView.adapter=adapter
    }
}