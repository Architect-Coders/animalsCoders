package com.architectcoders.animalcoders.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.architectcoders.animalcoders.login.LoginViewModel
import com.architectcoders.domain.interactors.LoginInteractor


@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val loginInteractor: LoginInteractor) :
    ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(loginInteractor) as T
    }
}