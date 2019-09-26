package com.architectcoders.animalcoders.main

import com.architectcoders.domain.model.Animal

sealed class MainActivityViewTransition {
    object NavigateToLogin : MainActivityViewTransition()
    object NavigateToProfile : MainActivityViewTransition()
    object NavigateToSearch : MainActivityViewTransition()
    object NavigateToFavourites : MainActivityViewTransition()
    class NavigateToAnimalDetail(val animal: Animal) : MainActivityViewTransition()
}