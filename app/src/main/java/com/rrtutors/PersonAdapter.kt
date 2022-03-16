package com.rrtutors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter(private val persons: List<Person>) : RecyclerView.Adapter<PersonAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: PersonAdapter.ViewHolder, position: Int) {
        val person = persons[position]
        holder.bind(person)
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val ivPic: ImageView = itemView.findViewById(R.id.ivImg)

        fun bind(person: Person) {
            tvName.text = person.name
            ivPic.setImageBitmap(person.image)

        }
    }
}