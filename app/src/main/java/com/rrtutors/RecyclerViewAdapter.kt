package com.rrtutors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(val emplyees: List<Emplyee>)
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : RecyclerViewAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_view,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.id.text = emplyees[position].id.toString()
        holder.name.text = emplyees[position].name
    }

    override fun getItemCount(): Int {
        return emplyees.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        var id=itemView.findViewById<TextView>(R.id.tvId);
        var name=itemView.findViewById<TextView>(R.id.tvName);
        //val id = itemView.tvId
      //  val name = itemView.tvName
    }
}