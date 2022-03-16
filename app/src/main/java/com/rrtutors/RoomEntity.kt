package com.rrtutors

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employeeTbl")
data class Emplyee(
    @PrimaryKey
    var id:Long?,

    @ColumnInfo(name = "uuid")
    var name: String
)