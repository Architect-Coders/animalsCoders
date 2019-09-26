package com.architectcoders.domain.interactors

import com.architectcoders.domain.repository.AuthRepository

/**
 *      animalsCoders.
 *
 *  @author -   AMarinaG
 *  @since  -   2019-09-20
 */
class RegisterInteractor(private val authRepository: AuthRepository) {
    suspend fun invoke(username: String, password: String) =
        authRepository.createUser(username, password)
}
