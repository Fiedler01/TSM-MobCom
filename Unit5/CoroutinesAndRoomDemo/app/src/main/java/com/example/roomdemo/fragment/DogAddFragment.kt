package com.example.roomdemo.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdemo.R
import com.example.roomdemo.database.Dog
import com.example.roomdemo.database.DogViewModel
import com.example.roomdemo.retrofit.DogService
import kotlinx.android.synthetic.main.fragment_dog_add.*
import kotlinx.android.synthetic.main.fragment_dog_add.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DogAdd : Fragment() {

    private lateinit var mDogViewModel: DogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dog_add, container, false)

        mDogViewModel = ViewModelProvider(this).get(DogViewModel::class.java)

        view.button_add_dog.setOnClickListener {
            insertDogToDatabase()
        }

        return view
    }

    private fun insertDogToDatabase() {
        val dogName = editTextDogName.text.toString()
        val dogAge = editTextDogAge.text.toString().toInt()
        val dogBreed = editTextTextDogBreed.text.toString()

        if (inputCheckForEmpty(dogName,dogBreed)) {
            var image = ""

            CoroutineScope(IO).launch {
                image = getDogImageUrl()
                val dog = Dog(0, dogName, dogAge, dogBreed, image)
                mDogViewModel.addDog(dog)
            }

            Toast.makeText(requireContext(), "Successfully added your dog!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_dogAdd_to_dogList)
        } else {
            Toast.makeText(requireContext(), "Please fill all the fields to go forward", Toast.LENGTH_SHORT).show()
        }
    }


    private fun inputCheckForEmpty(dogName: String, dogBreed:String): Boolean {
        return !(TextUtils.isEmpty(dogName) && TextUtils.isEmpty(dogBreed))
    }

    private suspend fun getDogImageUrl(): String {
        var imageUrl = ""

        val result = getService().getDogImage()
        if (result.message != null) {
            imageUrl = result.message.toString()
        }

        return imageUrl
    }

    private fun getService(): DogService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breeds/image/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(DogService::class.java)
    }

}
