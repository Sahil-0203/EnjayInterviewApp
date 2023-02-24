package com.example.enjayinterviewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class CommomAdapterClass(private val list: List<CommomModelClass>) : RecyclerView.Adapter<CommomAdapterClass.ViewHolder>()
{
    inner class ViewHolder(itemview:View):RecyclerView.ViewHolder(itemview)
    {
        val textView=itemView.findViewById<TextView>(R.id.question_text)
        //val textView1=itemView.findViewById<TextView>(R.id.text_answer)
        val imageView=itemView.findViewById<ImageView>(R.id.imageview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val CommomModelClass=list[position]

        holder.textView.text=CommomModelClass.que

        holder.imageView.setOnClickListener {v->

            val builder=AlertDialog.Builder(v.rootView.context)
            val dialogView=LayoutInflater.from(v.rootView.context).inflate(R.layout.custom_dialog_box,null)

            val textview1=dialogView.findViewById<TextView>(R.id.text_answer)
            textview1.setText(CommomModelClass.ans)

            builder.setView(dialogView)
            builder.setCancelable(true)
            builder.show()
        }
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
}