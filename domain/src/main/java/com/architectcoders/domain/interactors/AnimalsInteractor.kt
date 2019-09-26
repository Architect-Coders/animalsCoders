package com.architectcoders.domain.interactors

import com.architectcoders.domain.model.Animal
import com.architectcoders.domain.repository.AnimalsRepository

class AnimalsInteractor(private val animalsRepository: AnimalsRepository) {

    suspend fun getAnimals() = animalsRepository.getAnimals()

    suspend fun getFavourites() = animalsRepository.getFavourites()

    suspend fun saveFavourite(animal: Animal) = animalsRepository.saveFavourite(animal)

    suspend fun deleteFavourite(id: Int) = animalsRepository.deleteFavourite(id)
}