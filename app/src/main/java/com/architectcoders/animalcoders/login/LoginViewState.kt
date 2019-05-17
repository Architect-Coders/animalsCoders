package com.architectcoders.animalcoders.login

sealed class LoginViewState {
    object EmptyFields : LoginViewState()
    object Loading : LoginViewState()
    object ClearUsername : LoginViewState()
    object ClearPassword : LoginViewState()
    object UsernameError : LoginViewState()
    object PasswordError : LoginViewState()
    object NavigateToHome : LoginViewState()
}