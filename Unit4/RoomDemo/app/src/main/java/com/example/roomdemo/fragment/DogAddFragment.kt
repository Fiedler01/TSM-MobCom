package com.example.roomdemo.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdemo.R
import com.example.roomdemo.database.Dog
import com.example.roomdemo.database.DogViewModel
import kotlinx.android.synthetic.main.fragment_dog_add.*
import kotlinx.android.synthetic.main.fragment_dog_add.view.*


class DogAdd : Fragment() {

    private lateinit var mDogViewModel: DogViewModel
    // to not initialize non null variables, we have to guarantee it will be initialized tho

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dog_add, container, false)

        mDogViewModel = ViewModelProvider(this)[DogViewModel::class.java]
//        mDogViewModel = ViewModelProvider(this)[DogViewModel::class.java]

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
            val dog = Dog(0, dogName, dogAge, dogBreed)
            mDogViewModel.addDog(dog)
            Toast.makeText(requireContext(), "Successfully added your dog!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_dogAdd_to_dogList) // simple to navigate
        } else {
            Toast.makeText(requireContext(), "Please fill all the fields to go forward", Toast.LENGTH_SHORT).show()
        }
    }

    // TODO validate age
    private fun inputCheckForEmpty(dogName: String, dogBreed:String): Boolean {
        return !(TextUtils.isEmpty(dogName) && TextUtils.isEmpty(dogBreed))
    }

}