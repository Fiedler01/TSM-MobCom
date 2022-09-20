package com.example.roomdemo.database

import androidx.lifecycle.LiveData

// before the Dao
class DogRepository(private val dogDao: DogDao) { // constructor in signature

    var allDogs: LiveData<List<Dog>> = dogDao.getAll()
    // LiveData, special observable: only updates if the activity is in active state.

    fun addDog(dog: Dog){
        dogDao.insert(dog)
    }

}