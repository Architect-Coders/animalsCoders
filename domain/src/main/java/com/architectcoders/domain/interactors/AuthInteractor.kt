package com.architectcoders.domain.interactors

import com.architectcoders.domain.repository.AuthRepository

class AuthInteractor(private val authRepository: AuthRepository) {

    suspend fun getCurrentUser() = authRepository.getCurrentUser()

    suspend fun logOutCurrentUser() = authRepository.loOutCurrentUser()

}