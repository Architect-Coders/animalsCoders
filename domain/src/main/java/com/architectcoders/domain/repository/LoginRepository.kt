package com.architectcoders.domain.repository
import arrow.core.Either
import com.architectcoders.domain.model.Failure
import com.architectcoders.domain.model.User

interface LoginRepository {

    interface OnLoginRepositoryListener {
        fun onError()
        fun onSuccess()
    }

    suspend fun login(username: String?, password: String?): Either<Failure, User>
}
