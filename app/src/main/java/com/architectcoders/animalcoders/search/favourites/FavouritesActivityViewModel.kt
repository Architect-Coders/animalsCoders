package com.architectcoders.animalcoders.search.favourites

import android.util.Log
import arrow.core.Either
import com.architectcoders.domain.interactors.AnimalsInteractor
import com.example.baseandroid.coroutines.CoroutineDispatchers
import com.example.baseandroid.viewmodel.BaseViewModel
import kotlinx.coroutines.Job

class FavouritesActivityViewModel(
    private val interactor: AnimalsInteractor,
    dispatchers: CoroutineDispatchers
) :
    BaseViewModel<FavouritesActivityViewState, FavouritesActivityViewTransition>(dispatchers = dispatchers) {

    private var serviceCall: Job? = null

    override fun initServices() {
        super.initServices()
        getAnimals()
    }


    private fun getAnimals() {
        serviceCall = executeBackground {
            when (val result = interactor.getFavourites()) {
                is Either.Left -> {
                    executeUI {
                        Log.d("REASON: ", result.a.reason.toString())
                    }
                }
                is Either.Right -> {
                    executeUI {
                        Log.d("EMAIL: ", result.b.toString())
                        viewState.value = FavouritesActivityViewState.FavouritesLoaded(result.b)
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        serviceCall?.cancel()
    }
}