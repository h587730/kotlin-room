package com.rrtutors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import org.jetbrains.anko.doAsync
import java.util.*

class MainActivity : AppCompatActivity() {
lateinit var recyclerView:RecyclerView;
lateinit var btn:Button;
    private lateinit var model: EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        btn=findViewById<Button>(R.id.btn)
        // Get the view model
        val modelfactory=EmployeeViewModelFactory(application);

        model = ViewModelProvider(this,modelfactory).get(EmployeeViewModel::class.java)

        // Specify layout for recycler view
        val linearLayoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL,false)
        recyclerView.layoutManager = linearLayoutManager

        // Observe the model
        model.allEmplyees.observe(this, Observer{ students->
            // Data bind the recycler view
            recyclerView.adapter = RecyclerViewAdapter(students)
        })

        // Insert data into table
        btn.setOnClickListener {
            doAsync {
                model.insert(Emplyee(null, UUID.randomUUID().toString()))
            }
        }
    }
}