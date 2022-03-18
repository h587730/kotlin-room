package com.rrtutors.Viewmodels

import androidx.lifecycle.LiveData
import android.app.Application
import androidx.lifecycle.ViewModel
import com.rrtutors.Person
import com.rrtutors.Database.RoomSingleton


class PersonViewModel(application:Application):ViewModel()
{
    private val db: RoomSingleton = RoomSingleton.getInstance(application)
    internal val allPersons : LiveData<List<Person>> = db.personDao().allPersons()

    fun insert(person: Person){
        db.personDao().insert(person)
    }
}
