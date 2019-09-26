package com.architectcoders.animalcoders.search.favourites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.architectcoders.animalcoders.R
import com.architectcoders.domain.model.Animal
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.animals_item.view.*

class FavouritesAdapter(val animals: List<Animal>) :
    RecyclerView.Adapter<FavouritesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.animals_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animal = animals[position]
        holder.bind(animal)
    }

    override fun getItemCount(): Int = animals.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(animal: Animal) {
            Glide.with(itemView).load(animal.pictureUrl).into(itemView.animalPicture)
            itemView.animalName.text = animal.name
            itemView.animalAge.text =
                itemView.context.getString(R.string.animal_age_text, animal.age.capitalize())
            itemView.animalGender.text =
                itemView.context.getString(R.string.animal_gender_text, animal.gender.toString().capitalize())
        }
    }
}