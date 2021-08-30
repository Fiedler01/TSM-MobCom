package com.example.roomdemo.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
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
        dogRepository.addDog(dog)
    }
}


/*


fun addDog(dog: Dog) {
    CoroutineScope(IO).launch {// this is using kotlin coroutines, it will basically run the code in a background thread
        dogRepository.addDog(dog)
    }
}



       |
       |
       |
       v



suspend fun addDog(dog: Dog) {
        dogRepository.addDog(dog)
}



*/