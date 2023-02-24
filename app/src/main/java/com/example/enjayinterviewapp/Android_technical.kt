package com.example.enjayinterviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Android_technical : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_technical)

        val recyclerView=findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView.layoutManager=LinearLayoutManager(this)

        val data=ArrayList<CommomModelClass>()

        data.add(CommomModelClass("1. What is Android?",R.string.android1))
        data.add(CommomModelClass("2. Official Programming Language used to build Android Application?",R.string.android2))
        data.add(CommomModelClass("3. What is an activity?",R.string.android3))
        data.add(CommomModelClass("4. What is a service in Android?",R.string.android4))
        data.add(CommomModelClass("5. What are the core building blocks of android?",R.string.android5))
        data.add(CommomModelClass("6. What are the life cycle methods of android activity?",R.string.android6))
        data.add(CommomModelClass("7. What is intent?",R.string.android7))


        var adapter=CommomAdapterClass(data)

        recyclerView.adapter=adapter
    }
}