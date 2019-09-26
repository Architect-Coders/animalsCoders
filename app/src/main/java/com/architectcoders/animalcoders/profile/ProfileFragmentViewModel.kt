package com.architectcoders.animalcoders.profile

import android.util.Log
import arrow.core.Either
import com.architectcoders.domain.interactors.AuthInteractor
import com.architectcoders.domain.model.Failure
import com.example.baseandroid.coroutines.CoroutineDispatchers
import com.example.baseandroid.viewmodel.BaseViewModel
import kotlinx.coroutines.Job

class ProfileFragmentViewModel(private val authInteractor: AuthInteractor, dispatchers: CoroutineDispatchers) : BaseViewModel<ProfileFragmentViewState, ProfileFragmentViewTransition>(dispatchers = dispatchers) {

    private var serviceAuthCall: Job? = null

    override fun initServices() {
        super.initServices()
        serviceAuthCall = executeBackground {
            when (val result = authInteractor.getCurrentUser()) {
                is Either.Left -> {
                    executeUI {
                        Log.d("REASON: ", result.a.reason.toString())
                        if (result.a.reason == Failure.Reason.USER_NOT_EXIST) viewTransition.value =
                            ProfileFragmentViewTransition.NavigateToLogin
                    }
                }
                is Either.Right -> {
                    Log.d("EMAIL: ", result.b.email)
                    executeUI {
                        viewState.value = ProfileFragmentViewState.UserLoaded(result.b.email)
                    }
                }
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        serviceAuthCall?.cancel()
    }

    fun onFavouritesClicked() {
        viewTransition.value = ProfileFragmentViewTransition.NavigateToFavourites
    }
}
