package com.example.roomdemo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DogDao {
    @Query("SELECT * FROM Dog")
    fun getAll(): LiveData<List<Dog>>

    @Insert
    fun insert(dogs: Dog)

    @Delete
    fun delete(dog: Dog)
}