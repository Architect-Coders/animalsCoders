package com.architectcoders.animalcoders.login

import android.util.Log
import androidx.lifecycle.ViewModel
import arrow.core.Either
import com.architectcoders.animalcoders.tools.Scope
import com.architectcoders.domain.interactors.LoginInteractor
import kotlinx.coroutines.launch

class LoginViewModel(private val interactor: LoginInteractor) : ViewModel(), Scope by Scope.Impl {


    init {
        initScope()
    }

    fun validateCredencials(username: String, password: String) = launch {
        val result = interactor.login(username, password)
        when (result) {
            is Either.Left -> {
                Log.d("REASON: ", result.a.reason.toString())
            }
            is Either.Right -> {
                Log.d("EMAIL: ", result.b.email)
            }
        }

    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

}