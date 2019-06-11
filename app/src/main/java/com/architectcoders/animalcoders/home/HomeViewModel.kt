package com.architectcoders.animalcoders.home

import androidx.lifecycle.ViewModel
import com.architectcoders.animalcoders.tools.Scope
import com.architectcoders.domain.interactors.AnimalsInteractor

class HomeViewModel(private val animalsInteractor: AnimalsInteractor)  : ViewModel(), Scope by Scope.Impl