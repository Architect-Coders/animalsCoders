package com.architectcoders.animalcoders.login

import android.util.Log
import arrow.core.Either
import com.architectcoders.domain.interactors.LoginInteractor
import com.architectcoders.domain.model.Failure
import com.example.baseandroid.coroutines.CoroutineDispatchers
import com.example.baseandroid.viewmodel.BaseViewModel
import kotlinx.coroutines.Job

class LoginViewModel(private val interactor: LoginInteractor, dispatchers: CoroutineDispatchers) :
    BaseViewModel<LoginViewState, LoginViewTransition>(dispatchers = dispatchers) {

    private var serviceCall: Job? = null

    fun initView() {
        viewState.value = LoginViewState.EmptyFields
    }

    fun cancelForm() {
        viewState.value = LoginViewState.EmptyFields
    }

    fun validateCredentials(username: String, password: String) {
        viewState.value = LoginViewState.Loading
        serviceCall = executeBackground {
            when (val result = interactor.login(username, password)) {
                is Either.Left -> {
                    executeUI {
                        Log.d("REASON: ", result.a.reason.toString())
                        when (result.a.reason) {
                            Failure.Reason.BLANK_INVALID_USER -> viewState.value =
                                LoginViewState.UsernameError
                            Failure.Reason.BLANK_INVALID_PASS -> viewState.value =
                                LoginViewState.PasswordError
                            Failure.Reason.USER_NOT_EXIST, Failure.Reason.CONNECTION_ISSUES -> viewState.value =
                                LoginViewState.Error(result.a.message)
                            else -> viewState.value = LoginViewState.Error(result.a.message)
                        }
                    }
                }
                is Either.Right -> {
                    executeUI {
                        Log.d("EMAIL: ", result.b.email)
                        viewState.value = LoginViewState.EmptyFields
                        viewTransition.value = LoginViewTransition.NavigateToHome
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