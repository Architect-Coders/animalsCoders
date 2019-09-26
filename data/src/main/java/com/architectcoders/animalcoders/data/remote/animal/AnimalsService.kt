package com.architectcoders.animalcoders.data.remote.animal

import com.architectcoders.animalcoders.data.model.AnimalResponse
import retrofit2.Response

interface AnimalsService {
    suspend fun getAnimals(): Response<AnimalResponse>
}