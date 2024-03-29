package com.example.roomdemo.database

import androidx.lifecycle.LiveData

class DogRepository(private val dogDao: DogDao) {

    var allDogs: LiveData<List<Dog>> = dogDao.getAll()

    fun addDog(dog: Dog){
        dogDao.insert(dog)
    }

}