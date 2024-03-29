package com.example.roomdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.R
import com.example.roomdemo.database.DogViewModel
import kotlinx.android.synthetic.main.fragment_dog_list.view.*

class DogList : Fragment() {

    private lateinit var mDogViewModel: DogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dog_list, container, false)

        val adapter = DogRecyclerViewAdapter()
        val recyclerView = view.recycler_view_dog_list
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mDogViewModel = ViewModelProvider(this)[DogViewModel::class.java]
        mDogViewModel.readAllDog.observe(viewLifecycleOwner) { dog -> adapter.setData(dog) }
        // observing changes in the LiveData


        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_dogList_to_dogAdd)
        }

        return view
    }

}
