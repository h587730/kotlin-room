package com.rrtutors

import androidx.lifecycle.LiveData
import android.app.Application
import androidx.lifecycle.ViewModel


class EmployeeViewModel(var application:Application):ViewModel()
{
    private val db:RoomSingleton = RoomSingleton.getInstance(application)
    internal val allEmplyees : LiveData<List<Emplyee>> = db.employeeDao().allEmplyees()

    fun insert(student:Emplyee){
        db.employeeDao().insert(student)
    }
}
