package com.architectcoders.animalcoders.data.remote.animal

import com.architectcoders.animalcoders.data.remote.animal.database.AnimalsDatabase
import com.architectcoders.animalcoders.data.remote.animal.database.toDomainAnimal
import com.architectcoders.animalcoders.data.remote.animal.database.toRoomAnimal
import com.architectcoders.domain.model.Animal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavouritesServiceImpl(db: AnimalsDatabase) : FavouritesService {

    private val animalDao = db.animalDao()

    override suspend fun getFavourite(): List<Animal> {
        return withContext(Dispatchers.IO) {
            animalDao.getAll().map { it.toDomainAnimal() }
        }
    }

    override suspend fun saveFavourite(animal: Animal) {
        withContext(Dispatchers.IO) { animalDao.insertAnimal(animal = animal.toRoomAnimal()) }
    }

    override suspend fun deleteFavourite(id: Int) {
        withContext(Dispatchers.IO) { animalDao.removeById(id) }
    }

}