package com.architectcoders.animalcoders.search.detail

import com.architectcoders.domain.model.Animal
import com.example.baseandroid.coroutines.CoroutineDispatchers
import com.example.baseandroid.viewmodel.BaseViewModel

class AnimalDetailActivityViewModel(
    val animal: Animal?,
    dispatchers: CoroutineDispatchers
) :
    BaseViewModel<AnimalDetailActivityViewState, AnimalDetailActivityViewTransition>(dispatchers = dispatchers) {

    override fun init() {
        viewState.value = AnimalDetailActivityViewState.ViewLoaded(animal)
    }

}