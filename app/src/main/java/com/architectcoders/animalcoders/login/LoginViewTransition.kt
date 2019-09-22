package com.architectcoders.animalcoders.login



sealed class LoginViewTransition {
    object NavigateToHome : LoginViewTransition()
}