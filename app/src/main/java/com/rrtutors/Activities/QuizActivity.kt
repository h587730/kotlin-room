package com.rrtutors.Activities

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import com.rrtutors.Adapter.PersonAdapter
import com.rrtutors.Person
import com.rrtutors.R
import com.rrtutors.Viewmodel.QuizViewModel
import com.rrtutors.Viewmodel.ViewModelFactory
import org.jetbrains.anko.attempt
import org.jetbrains.anko.doAsync

private const val TAG1 = "testQuizLogic"

class QuizActivity : AppCompatActivity() {

    private lateinit var ivQuizPic: ImageView
    private lateinit var btnAlt1: Button
    private lateinit var btnAlt2: Button
    private lateinit var btnAlt3: Button
    private lateinit var btnEnd: Button
    private lateinit var tvAttemptsX: TextView
    private lateinit var tvScoreX: TextView


    private lateinit var viewmodel: QuizViewModel
    //private lateinit var personList: List<Person>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        ivQuizPic = findViewById(R.id.ivQuizPic)
        btnAlt1 = findViewById(R.id.btnAlt1)
        btnAlt2 = findViewById(R.id.btnAlt2)
        btnAlt3 = findViewById(R.id.btnAlt3)
        btnEnd = findViewById(R.id.btnEnd)
        tvAttemptsX = findViewById(R.id.tvAttemptsX)
        tvScoreX = findViewById(R.id.tvScoreX)

        val vmFactory = ViewModelFactory(application)
        viewmodel = ViewModelProvider(this, vmFactory).get(QuizViewModel::class.java)

        if (viewmodel.rounds == 0) {
            nextRound()
        }


        btnAlt1.setOnClickListener {
            Log.i(TAG1, "${viewmodel.personList.component1()} equals ${viewmodel.correctPerson.value}")
            if (viewmodel.correctPerson.value?.image == (ivQuizPic.drawable as BitmapDrawable).bitmap) {
                viewmodel.increaseScore()
            }
            viewmodel.increaseAttempts()
            viewmodel.rounds++
            Log.i(TAG1, "Rounds: ${viewmodel.rounds}")
            nextRound()
        }

        btnAlt2.setOnClickListener {
            Log.i(TAG1, "${viewmodel.personList.component2()} equals ${viewmodel.wrongPerson1.value}")
            if (viewmodel.wrongPerson1.value?.image == (ivQuizPic.drawable as BitmapDrawable).bitmap) {
                viewmodel.increaseScore()
            }
            viewmodel.increaseAttempts()
            viewmodel.rounds++
            Log.i(TAG1, "Rounds: ${viewmodel.rounds}")
            nextRound()
        }

        btnAlt3.setOnClickListener {
            Log.i(TAG1, "${viewmodel.personList.component3()} equals ${viewmodel.wrongPerson2.value}")
            if (viewmodel.wrongPerson2.value?.image == (ivQuizPic.drawable as BitmapDrawable).bitmap) {
                viewmodel.increaseScore()
            }
            viewmodel.increaseAttempts()
            viewmodel.rounds++
            Log.i(TAG1, "Rounds: ${viewmodel.rounds}")
            nextRound()
        }


        btnEnd.setOnClickListener{
            finish()

        }


        viewmodel.score.observe(this) { score ->
            tvScoreX.text = score.toString()
        }

        viewmodel.attempts.observe(this) { attempts ->
            tvAttemptsX.text = attempts.toString()
        }


        viewmodel.correctPerson.observe(this) { correctPerson ->
            btnAlt1.text = correctPerson.name
        }

        viewmodel.correctPerson.observe(this){ correctPerson ->
            ivQuizPic.setImageBitmap(correctPerson.image)
            Log.i(TAG1, "Correct image: ${correctPerson.image}")
        }

        viewmodel.wrongPerson1.observe(this) { wrongPerson1 ->
            btnAlt2.text = wrongPerson1.name
        }

        viewmodel.wrongPerson2.observe(this){ wrongPerson2 ->
            btnAlt3.text = wrongPerson2.name
        }


    }

    private fun nextRound() {
        viewmodel.allPersons.observe(this) { allPersons ->
            viewmodel.personList = allPersons
            Log.i(TAG1, "${viewmodel.personList}")
            viewmodel.assignQuizValues(viewmodel.shufflePersonList())

        }
    }



}