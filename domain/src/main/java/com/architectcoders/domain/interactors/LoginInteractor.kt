package com.architectcoders.domain.interactors

import arrow.core.Either
import com.architectcoders.domain.model.Failure
import com.architectcoders.domain.repository.AuthRepository

class LoginInteractor(private val authRepository: AuthRepository) {

    suspend fun login(username: String, password: String) =
        when {
            username.isEmpty() -> Either.left(Failure(Failure.Reason.BLANK_INVALID_USER))
            password.isEmpty() -> Either.left(Failure(Failure.Reason.BLANK_INVALID_PASS))
            else -> authRepository.login(username, password)
        }
    
}