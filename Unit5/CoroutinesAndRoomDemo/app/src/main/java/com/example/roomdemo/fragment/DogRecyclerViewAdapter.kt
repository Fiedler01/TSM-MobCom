package com.example.roomdemo.fragment

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.drawToBitmap
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.R
import com.example.roomdemo.database.Dog
import kotlinx.android.synthetic.main.dog_row.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL


class DogRecyclerViewAdapter: RecyclerView.Adapter<DogRecyclerViewAdapter.DogViewHolder>() {

    private var dogList: List<Dog> = emptyList()

    class DogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        return DogViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.dog_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val currentItem = dogList[position]
        holder.itemView.textViewDogId.text = currentItem.id.toString()
        holder.itemView.textViewDogName.text = currentItem.name
        holder.itemView.textViewDogBreed.text = currentItem.breed
        holder.itemView.textViewDogAge.text = currentItem.age.toString()
        CoroutineScope(IO).launch {
            val url = URL(currentItem.image)
            val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            withContext(Main) {
                holder.itemView.imageViewDog.setImageBitmap(bmp)
            }
        }

    }

    fun setData(dogList: List<Dog>) {
        this.dogList = dogList
        notifyDataSetChanged()
    }
}