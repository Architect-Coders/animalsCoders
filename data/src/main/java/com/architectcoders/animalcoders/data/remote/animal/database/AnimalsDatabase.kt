package com.architectcoders.animalcoders.data.remote.animal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.architectcoders.animalcoders.data.model.Animal

@Database(entities = [Animal::class], version = 1)
abstract class AnimalsDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            AnimalsDatabase::class.java,
            "animals-db"
        ).build()
    }

    abstract fun animalDao(): AnimalDao
}