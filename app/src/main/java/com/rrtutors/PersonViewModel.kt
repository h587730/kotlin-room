package com.rrtutors

import androidx.lifecycle.LiveData
import android.app.Application
import androidx.lifecycle.ViewModel


class PersonViewModel(application:Application):ViewModel()
{
    private val db:RoomSingleton = RoomSingleton.getInstance(application)
    internal val allPersons : LiveData<List<Person>> = db.personDao().allPersons()

    fun insert(person: Person){
        db.personDao().insert(person)
    }
}
