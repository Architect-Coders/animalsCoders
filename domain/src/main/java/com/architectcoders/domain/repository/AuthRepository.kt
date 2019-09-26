package com.architectcoders.domain.repository

import arrow.core.Either
import com.architectcoders.domain.model.Failure
import com.architectcoders.domain.model.User

interface AuthRepository {

    suspend fun login(username: String, password: String): Either<Failure, User>

    suspend fun getCurrentUser(): Either<Failure, User>

    suspend fun createUser(username: String, password: String): Either<Failure, User>

    suspend fun loOutCurrentUser()

}
