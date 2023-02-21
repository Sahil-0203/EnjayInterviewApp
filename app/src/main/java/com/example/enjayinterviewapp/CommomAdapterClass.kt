package com.example.enjayinterviewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommomAdapterClass(private val list: List<CommomModelClass>) : RecyclerView.Adapter<CommomAdapterClass.ViewHolder>()
{

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val CommomModelClass=list[position]

        holder.textView.text=CommomModelClass.text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val View=LayoutInflater.from(parent.context).inflate(R.layout.question_recycleview,parent,false)

        return ViewHolder(View)
    }

    override fun getItemCount(): Int
    {
        return list.size
    }

    class ViewHolder(itemview:View):RecyclerView.ViewHolder(itemview)
    {
        val textView=itemView.findViewById<TextView>(R.id.question_text)
    }

}