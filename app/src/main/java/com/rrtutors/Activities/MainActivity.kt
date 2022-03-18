package com.rrtutors.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rrtutors.Adapter.PersonAdapter
import com.rrtutors.R
import com.rrtutors.Viewmodels.PersonViewModel
import com.rrtutors.Viewmodels.PersonViewModelFactory

private const val TAG1 = "onCreateOptionsMenu"
private const val TAG2 = "onOptionsItemSelected"
private const val TAG3 = "PersonRecieved"
private const val TAG4 = "showNewList"

class MainActivity : AppCompatActivity() {

    private var ADD_REQ: Int = 42
    private var QUIZ_REQ: Int = 43

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        Log.i(TAG1, "Menu created")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Add Picture to database
        if (item.itemId == R.id.miAdd) {
            // Navigate to AddActivity
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, ADD_REQ)
            Log.i(TAG2, "Add picture selected")
            return true
        }
        //Go to quiz
        if (item.itemId == R.id.miQuiz) {
            // Navigate to QuizActivity
            val intent = Intent(this, QuizActivity::class.java)
            startActivityForResult(intent, QUIZ_REQ)
            Log.i(TAG2, "Quiz item selected")
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}


