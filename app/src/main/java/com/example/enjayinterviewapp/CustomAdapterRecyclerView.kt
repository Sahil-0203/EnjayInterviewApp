package com.example.enjayinterviewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapterRecyclerView(private val mList: List<ModelClassRecyclerView>,val listner:MyClickListner) :
    RecyclerView.Adapter<CustomAdapterRecyclerView.ViewHolder>()
{
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView)
    {
        val imageView: ImageView = itemView.findViewById(R.id.recycler_image)
        val textView: TextView = itemView.findViewById(R.id.recycler_textview)

        init {
            itemView.setOnClickListener{
                val position=adapterPosition
                listner.onClick(position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview,parent,false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val ModelClassRecyclerView = mList[position]

        holder.imageView.setImageResource(ModelClassRecyclerView.image)
        holder.textView.text=ModelClassRecyclerView.text

    }

    override fun getItemCount(): Int
    {
        return mList.size
    }

    interface MyClickListner{
        fun onClick(position: Int)
    }


}