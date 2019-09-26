package com.architectcoders.animalcoders.register

sealed class RegisterViewTransition {
    object NavigateToHome : RegisterViewTransition()
}