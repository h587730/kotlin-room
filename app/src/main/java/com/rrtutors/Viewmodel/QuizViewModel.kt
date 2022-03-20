package com.rrtutors.Viewmodel

import android.app.Application
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.lifecycle.*
import com.rrtutors.Person
import com.rrtutors.Database.RoomSingleton
import com.rrtutors.R
import kotlinx.coroutines.launch
import java.util.*

private const val TAG1 = "testVM"


class QuizViewModel(application:Application) : ViewModel() {

    private val db: RoomSingleton = RoomSingleton.getInstance(application)

    fun insert(person: Person) = viewModelScope.launch {
        db.personDao().insert(person)
    }

    fun delete(person: Person) = viewModelScope.launch {
        db.personDao().delete(person)
    }

    private var _allPersons: LiveData<List<Person>> = db.personDao().allPersons()
    val allPersons: LiveData<List<Person>>
        get() = _allPersons


    private var _score = MutableLiveData(0)
    val score: LiveData<Int>
        get() = _score

    private var _attempts = MutableLiveData(0)
    val attempts: LiveData<Int>
        get() = _attempts


    private var _randomList = MutableLiveData<MutableList<Person>>()
    val randomList: MutableLiveData<MutableList<Person>>
        get() = _randomList


    /*private var _shuffledPersonList = Transformations.switchMap(_allPersons) { list ->
        val newLiveData = MutableLiveData<List<Person>>()
        val newList = list.toMutableList()
        newList.shuffle()
        newList.subList(0, 3)
        newLiveData.value = newList
        return@switchMap newLiveData
    }
    val shuffledPersonList: LiveData<List<Person>>
        get() = _shuffledPersonList*/


    private lateinit var _correctPerson: LiveData<Person>

    val correctPerson: LiveData<Person>
        get() = _correctPerson


    private lateinit var _wrongPerson1: LiveData<Person>

    val wrongPerson1: LiveData<Person>
        get() = _wrongPerson1

    private lateinit var _wrongPerson2: LiveData<Person>

    val wrongPerson2: LiveData<Person>
        get() = _wrongPerson2


    private fun shuffledPersonList(): LiveData<List<Person>> {
        val shuffledPersonList = Transformations.switchMap(_allPersons) { list ->
            val newLiveData = MutableLiveData<List<Person>>()
            val newList = list.toMutableList()
            newList.shuffle()
            newList.subList(0, 3)
            newLiveData.value = newList
            return@switchMap newLiveData
        }
        return shuffledPersonList
    }

    fun assignQuizValues() {
        val shuffledPersonList: LiveData<List<Person>> = shuffledPersonList()

        _correctPerson = Transformations.switchMap(shuffledPersonList) { list ->
            val newCorrectPerson = MutableLiveData<Person>()
            val newList = list.toMutableList()
            newCorrectPerson.value = newList.component1()
            return@switchMap newCorrectPerson
        }

        _wrongPerson1 = Transformations.switchMap(shuffledPersonList) { list ->
            val newWrongPerson1 = MutableLiveData<Person>()
            val newList = list.toMutableList()
            newWrongPerson1.value = newList.component1()
            return@switchMap newWrongPerson1
        }
        _wrongPerson2 = Transformations.switchMap(shuffledPersonList) { list ->
            val newWrongPerson1 = MutableLiveData<Person>()
            val newList = list.toMutableList()
            newWrongPerson1.value = newList.component1()
            return@switchMap newWrongPerson1


        }

        Log.i(TAG1, "Correctperson: ${correctPerson.value}, wrongPerson1: ${wrongPerson1.value}" +
                ", wrongPerson2: ${wrongPerson2.value}")

    }
}


