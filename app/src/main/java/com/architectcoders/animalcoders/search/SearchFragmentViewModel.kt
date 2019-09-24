package com.architectcoders.animalcoders.search

import android.util.Log
import arrow.core.Either
import com.architectcoders.animalcoders.login.LoginViewState
import com.architectcoders.animalcoders.login.LoginViewTransition
import com.architectcoders.domain.interactors.AnimalsInteractor
import com.architectcoders.domain.model.Failure
import com.example.baseandroid.coroutines.CoroutineDispatchers
import com.example.baseandroid.viewmodel.BaseViewModel
import kotlinx.coroutines.Job

class SearchFragmentViewModel(private val interactor: AnimalsInteractor, dispatchers: CoroutineDispatchers)
    : BaseViewModel<SearchFragmentViewState, SearchFragmentViewTransition>(dispatchers = dispatchers) {

    private var serviceCall: Job? = null


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
                    }
                }
            }
        }

    }


}
