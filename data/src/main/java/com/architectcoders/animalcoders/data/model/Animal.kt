package com.architectcoders.animalcoders.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Animal(
    @PrimaryKey val id: String,
    val name: String,
    val pictureUrl: String,
    val gender: String,
    val age: String,
    val description: String
)