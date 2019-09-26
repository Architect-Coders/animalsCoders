package com.architectcoders.animalcoders.search.detail

import com.architectcoders.domain.interactors.AnimalsInteractor
import com.architectcoders.domain.model.Animal
import com.example.baseandroid.coroutines.CoroutineDispatchers
import com.example.baseandroid.viewmodel.BaseViewModel
import kotlinx.coroutines.Job

class AnimalDetailActivityViewModel(
    val animal: Animal?,
    private val interactor: AnimalsInteractor,
    dispatchers: CoroutineDispatchers
) :
    BaseViewModel<AnimalDetailActivityViewState, AnimalDetailActivityViewTransition>(dispatchers = dispatchers) {

    private var serviceCall: Job? = null

    override fun init() {
        viewState.value = AnimalDetailActivityViewState.ViewLoaded(animal)
    }

    override fun onCleared() {
        super.onCleared()
        serviceCall?.cancel()
    }

    fun onFavoriteClicked() {
        animal?.let {
            serviceCall = executeBackground {
                interactor.saveFavourite(it)
            }
        }
    }

}