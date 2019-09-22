package com.architectcoders.animalcoders.data.repository

import arrow.core.Either
import com.architectcoders.animalcoders.data.remote.auth.AuthService
import com.architectcoders.animalcoders.data.remote.login.LoginService
import com.architectcoders.domain.model.Failure
import com.architectcoders.domain.model.User
import com.architectcoders.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val loginService: LoginService,
    private val authService: AuthService
) : AuthRepository {

    override suspend fun login(username: String, password: String): Either<Failure, User> {
        return when (val result = loginService.login(username, password)) {
            is Either.Left -> {
                Either.left(result.a)
            }
            is Either.Right -> {
                Either.right(User(result.b.email ?: "", result.b.displayName ?: ""))
            }
        }
    }

    override suspend fun getCurrentUser(): Either<Failure, User> {
        return when (val result = authService.getCurrentUser()) {
            is Either.Left -> {
                Either.left(result.a)
            }
            is Either.Right -> {
                Either.right(User(result.b.email ?: "", result.b.displayName ?: ""))
            }
        }
    }

    override suspend fun loOutCurrentUser() {
        authService.logOutCurrentUser()
    }

    override suspend fun createUser(username: String, password: String): Either<Failure, Unit> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}