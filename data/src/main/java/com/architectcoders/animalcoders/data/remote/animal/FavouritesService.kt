package com.architectcoders.animalcoders.data.remote.animal

import com.architectcoders.domain.model.Animal

interface FavouritesService {
    suspend fun getFavourite() : List<Animal>

    suspend fun saveFavourite(animal: Animal)

    suspend fun deleteFavourite(id: Int)
}