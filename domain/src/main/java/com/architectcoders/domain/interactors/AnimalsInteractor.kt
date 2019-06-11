package com.architectcoders.domain.interactors

import com.architectcoders.domain.repository.AnimalsRepository

class AnimalsInteractor(private val animalsRepository: AnimalsRepository) {

    suspend fun getAnimals() {
        TODO()
    }
}