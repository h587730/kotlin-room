package com.rrtutors

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.AnkoAsyncContext
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView;
    private lateinit var model: PersonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        // Get the view model

        val modelfactory = PersonViewModelFactory(application)
        model = ViewModelProvider(this, modelfactory).get(PersonViewModel::class.java)


        // Specify layout for recycler view
        val linearLayoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        )
        recyclerView.layoutManager = linearLayoutManager


        // Observe the model
        model.allPersons.observe(this, Observer { persons ->
            // Data bind the recycler view
            recyclerView.adapter = PersonAdapter(persons)
        })


    }

}


