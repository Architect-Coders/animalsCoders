package com.architectcoders.animalcoders.login

sealed class LoginViewState {
    object EmptyFields : LoginViewState()
    object Loading : LoginViewState()
    object UsernameError : LoginViewState()
    object PasswordError : LoginViewState()
    class Error(val errorMessage: String?) : LoginViewState()
    object NavigateToHome : LoginViewState()
}