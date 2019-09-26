package com.architectcoders.animalcoders.data.remote.animal.database

import androidx.room.*
import com.architectcoders.animalcoders.data.model.Animal

@Dao
interface AnimalDao {

    @Query("SELECT * FROM Animal")
    fun getAll(): List<Animal>

    @Query("SELECT * FROM Animal WHERE id = :id")
    fun findById(id: Int): Animal

    @Query("SELECT COUNT(id) FROM Animal")
    fun animalCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAnimal(animal: Animal)

    @Query("DELETE FROM Animal WHERE id = :id")
    fun removeById(id: Int)

    @Update
    fun updateAnimal(movie: Animal)
}