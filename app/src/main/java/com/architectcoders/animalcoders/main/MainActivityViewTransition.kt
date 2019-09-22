package com.architectcoders.animalcoders.main



sealed class MainActivityViewTransition {
    object NavigateToLogin : MainActivityViewTransition()
    object NavigateToProfile : MainActivityViewTransition()
    object NavigateToMap : MainActivityViewTransition()
    object NavigateToSearch : MainActivityViewTransition()
}