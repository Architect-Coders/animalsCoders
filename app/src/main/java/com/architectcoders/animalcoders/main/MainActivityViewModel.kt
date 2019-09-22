package com.architectcoders.animalcoders.main

import android.util.Log
import arrow.core.Either
import com.architectcoders.domain.interactors.AuthInteractor
import com.architectcoders.domain.model.Failure
import com.example.baseandroid.coroutines.CoroutineDispatchers
import com.example.baseandroid.viewmodel.BaseViewModel
import kotlinx.coroutines.Job

class MainActivityViewModel(
    private val authInteractor: AuthInteractor,
    dispatchers: CoroutineDispatchers
) :
    BaseViewModel<MainActivityViewState, MainActivityViewTransition>(dispatchers = dispatchers) {

    private var serviceAuthCall: Job? = null
    private var serviceLogOutCall: Job? = null

    fun initView() {
        viewState.value = MainActivityViewState.InitialState
        serviceAuthCall = executeBackground {
            when (val result = authInteractor.getCurrentUser()) {
                is Either.Left -> {
                    executeUI {
                        Log.d("REASON: ", result.a.reason.toString())
                        if (result.a.reason == Failure.Reason.BLANK_INVALID_USER) viewTransition.value =
                            MainActivityViewTransition.NavigateToLogin
                    }
                }
                is Either.Right -> {
                    executeUI {
                        Log.d("EMAIL: ", result.b.email)
                        viewTransition.value = MainActivityViewTransition.NavigateToSearch

                    }
                }
            }
        }
    }

    fun logOut() {
        serviceLogOutCall = executeBackground {
            authInteractor.logOutCurrentUser()
        }
        viewTransition.value =
            MainActivityViewTransition.NavigateToLogin
    }

    override fun onCleared() {
        super.onCleared()
        serviceAuthCall?.cancel()
        serviceLogOutCall?.cancel()
    }

}