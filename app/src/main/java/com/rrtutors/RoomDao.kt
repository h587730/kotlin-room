package com.rrtutors

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmployeeDao{
    @Query("SELECT * FROM employeeTbl ORDER BY id DESC")
    fun allEmplyees(): LiveData<List<Emplyee>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: Emplyee)
}