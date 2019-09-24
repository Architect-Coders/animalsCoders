package com.architectcoders.animalcoders.data.remote.animal

import com.architectcoders.animalcoders.data.model.AnimalResponse
import retrofit2.Response
import retrofit2.Retrofit

class AnimalsServiceImpl(retrofit: Retrofit) : AnimalsService {

    private val animal: AnimalsApiService = retrofit.create(AnimalsApiService::class.java)

    override suspend fun getAnimals(): Response<AnimalResponse> {
       return animal.getAnimals()
    }

}