package com.architectcoders.animalcoders.data.remote.animal

import com.architectcoders.animalcoders.data.model.AnimalResponse
import retrofit2.Response
import retrofit2.http.POST

interface AnimalsApiService {
    @POST("animals/search/?fields[animals]=name,url,pictureThumbnailUrl,ageString,descriptionText,sex&fields[species]=singular")
    suspend fun getAnimals(): Response<AnimalResponse>
}