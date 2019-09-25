package com.architectcoders.animalcoders.search

import android.util.Log
import arrow.core.Either
import com.architectcoders.domain.interactors.AnimalsInteractor
import com.architectcoders.domain.model.Animal
import com.example.baseandroid.coroutines.CoroutineDispatchers
import com.example.baseandroid.viewmodel.BaseViewModel
import kotlinx.coroutines.Job

class SearchFragmentViewModel(
    private val interactor: AnimalsInteractor,
    dispatchers: CoroutineDispatchers
) : BaseViewModel<SearchFragmentViewState, SearchFragmentViewTransition>(dispatchers = dispatchers) {

    private var serviceCall: Job? = null

    override fun initServices() {
        super.initServices()
        getAnimals()
    }


    fun getAnimals() {
        serviceCall = executeBackground {
            when (val result = interactor.getAnimals()) {
                is Either.Left -> {
                    executeUI {
                        Log.d("REASON: ", result.a.reason.toString())
                    }
                }
                is Either.Right -> {
                    executeUI {
                        Log.d("EMAIL: ", result.b.toString())
                        viewState.value = SearchFragmentViewState.DrawAnimals(result.b)
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        serviceCall?.cancel()
    }

    fun navigateToDetail(animal: Animal) {
        viewTransition.value = SearchFragmentViewTransition.NavigateToAnimalDetail(animal)
    }


}
