package com.architectcoders.domain.repository

import arrow.core.Either
import com.architectcoders.domain.model.Animal
import com.architectcoders.domain.model.Failure

interface AnimalsRepository {

    suspend fun getAnimals(): Either<Failure, List<Animal>>

}