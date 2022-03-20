package com.rrtutors.Activities

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import com.rrtutors.Person
import com.rrtutors.R
import com.rrtutors.Viewmodel.QuizViewModel
import com.rrtutors.Viewmodel.ViewModelFactory

private const val TAG1 = "testQuizLogic"

class QuizActivity : AppCompatActivity() {

    private lateinit var ivQuizPic: ImageView
    private lateinit var btnAlt1: Button
    private lateinit var btnAlt2: Button
    private lateinit var btnAlt3: Button
    private lateinit var tvAttemptsX: TextView
    private lateinit var tvScoreX: TextView

    private lateinit var viewmodel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        ivQuizPic = findViewById(R.id.ivQuizPic)
        btnAlt1 = findViewById(R.id.btnAlt1)
        btnAlt2 = findViewById(R.id.btnAlt2)
        btnAlt3 = findViewById(R.id.btnAlt3)
        tvAttemptsX = findViewById(R.id.tvAttemptsX)
        tvScoreX = findViewById(R.id.tvScoreX)

        val vmFactory = ViewModelFactory(application)
        viewmodel = ViewModelProvider(this, vmFactory).get(QuizViewModel::class.java)

        viewmodel.assignQuizValues()
        setCorrectImage()


        btnAlt1.setOnClickListener {

            finish()
            startActivity(this.intent)
        }

        btnAlt2.setOnClickListener {

            finish()
            startActivity(this.intent)
        }

        btnAlt3.setOnClickListener {

            finish()
            startActivity(this.intent)
        }


    }

    private fun setCorrectImage() {
        ivQuizPic.setImageBitmap(viewmodel.correctPerson.value?.image)
        Log.i(TAG1, "correct image: ${viewmodel.correctPerson.value?.image}")
    }



}