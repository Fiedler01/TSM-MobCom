package com.example.roomdemo.fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.R
import com.example.roomdemo.database.Dog
import kotlinx.android.synthetic.main.dog_row.view.*

class DogRecyclerViewAdapter: RecyclerView.Adapter<DogRecyclerViewAdapter.DogViewHolder>() {

    private var dogList: List<Dog> = emptyList() // convenient initializer

    class DogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        return DogViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.dog_row, parent, false))
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        // reference to the view items is found in the ViewHolder

        val currentItem = dogList[position]
        holder.itemView.textViewDogId.text = currentItem.id.toString()
        holder.itemView.textViewDogName.text = currentItem.name
        holder.itemView.textViewDogBreed.text = currentItem.breed
        holder.itemView.textViewDogAge.text = currentItem.age.toString()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(dogList: List<Dog>) {
        this.dogList = dogList
        notifyDataSetChanged()
    }
}