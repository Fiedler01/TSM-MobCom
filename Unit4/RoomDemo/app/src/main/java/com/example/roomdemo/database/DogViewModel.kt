package com.example.roomdemo.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DogViewModel(application: Application): AndroidViewModel(application) {
    val readAllDog: LiveData<List<Dog>>
    val dogRepository:DogRepository

    init {
        val dogDao = AppDatabase.getInstance(application).dogDao()
        dogRepository = DogRepository(dogDao)
        readAllDog = dogRepository.allDogs
    }

    fun addDog(dog: Dog) {
        viewModelScope.launch (Dispatchers.IO){// this is using kotlin coroutines, it will basically run the code in a background thread
            dogRepository.addDog(dog)
        }
    }
}