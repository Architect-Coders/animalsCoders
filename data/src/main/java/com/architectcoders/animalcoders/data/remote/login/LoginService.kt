package com.architectcoders.animalcoders.data.remote.login

import arrow.core.Either
import com.architectcoders.domain.model.Failure
import com.google.firebase.auth.FirebaseUser

interface LoginService {
    suspend fun login(username: String, password: String): Either<Failure, FirebaseUser>
    suspend fun register(username: String, password: String): Either<Failure, FirebaseUser>
}
