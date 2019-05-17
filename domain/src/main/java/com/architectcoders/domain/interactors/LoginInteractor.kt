package com.architectcoders.domain.interactors

import arrow.core.Either
import com.architectcoders.domain.model.Failure
import com.architectcoders.domain.repository.LoginRepository

class LoginInteractor(private val loginRepository: LoginRepository) {

    interface OnLoginFinishedListener {
        fun onUsernameError()
        fun onPasswordError()
        fun onUsernameSuccess()
        fun onPasswordSuccess()
        fun onSuccess()
    }

    suspend fun login(username: String, password: String) =
        when {
            username.isEmpty() -> Either.left(Failure(Failure.Reason.BLANK_INVALID_USER))
            password.isEmpty() -> Either.left(Failure(Failure.Reason.BLANK_INVALID_PASS))
            else -> loginRepository.login(username, password)
        }

}