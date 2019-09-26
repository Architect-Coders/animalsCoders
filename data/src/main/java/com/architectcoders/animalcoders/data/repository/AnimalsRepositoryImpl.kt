package com.architectcoders.animalcoders.data.repository

import arrow.core.Either
import com.architectcoders.animalcoders.data.remote.animal.AnimalsService
import com.architectcoders.animalcoders.data.remote.animal.FavouritesService
import com.architectcoders.domain.model.Animal
import com.architectcoders.domain.model.Failure
import com.architectcoders.domain.repository.AnimalsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimalsRepositoryImpl(
    private val animalsService: AnimalsService,
    private val favouritesService: FavouritesService
) : AnimalsRepository {

    override suspend fun getAnimals(): Either<Failure, List<Animal>> = withContext(Dispatchers.IO) {

        try {
            val response = animalsService.getAnimals()

            if (response.isSuccessful && response.body() != null) {
                val product = response.body()!!.map()
                return@withContext Either.right(product)
            } else
                return@withContext Either.left(Failure(Failure.Reason.API_ERROR))
        } catch (e: Exception) {
            return@withContext Either.left(Failure(Failure.Reason.API_ERROR))
        }

    }

    override suspend fun getFavourites(): Either<Failure, List<Animal>> {
        return Either.right(favouritesService.getFavourite())
    }

    override suspend fun saveFavourite(animal: Animal) {
        favouritesService.saveFavourite(animal)
    }

    override suspend fun deleteFavourite(id: Int) {
        favouritesService.deleteFavourite(id)
    }

}