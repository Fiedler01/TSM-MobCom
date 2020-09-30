package com.example.roomdemo.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dog (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val age: Int,
    val breed: String
)