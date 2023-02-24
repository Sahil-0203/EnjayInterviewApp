package com.example.enjayinterviewapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CommonQuestion : AppCompatActivity(), View.OnClickListener
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_question)

        val recyclerView=findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView.layoutManager=LinearLayoutManager(this)

        val data=ArrayList<CommomModelClass>()

        data.add(CommomModelClass("What is Java?"))
        data.add(CommomModelClass("Atharva"))
        data.add(CommomModelClass("Atharva"))
        data.add(CommomModelClass("Atharva"))
        data.add(CommomModelClass("Atharva"))
        data.add(CommomModelClass("Atharva"))
        data.add(CommomModelClass("Atharva"))
        data.add(CommomModelClass("Atharva"))
        data.add(CommomModelClass("Atharva"))
        data.add(CommomModelClass("Atharva"))
        data.add(CommomModelClass("Atharva"))
        data.add(CommomModelClass("Atharva"))
        data.add(CommomModelClass("Atharva"))
        data.add(CommomModelClass("Atharva"))
        data.add(CommomModelClass("Atharva"))
        data.add(CommomModelClass("Atharva"))
        data.add(CommomModelClass("Atharva"))
        data.add(CommomModelClass("Atharva"))
        data.add(CommomModelClass("Atharva"))
        data.add(CommomModelClass("Atharva"))

        val adapter=CommomAdapterClass(data)

        recyclerView.adapter=adapter
    }

    override fun onClick(p0: View?)
    {

    }
}