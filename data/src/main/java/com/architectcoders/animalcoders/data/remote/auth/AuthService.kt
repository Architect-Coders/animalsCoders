package com.architectcoders.animalcoders.data.remote.auth

import arrow.core.Either
import com.architectcoders.domain.model.Failure
import com.google.firebase.auth.FirebaseUser

interface AuthService {
    suspend fun getCurrentUser(): Either<Failure, FirebaseUser>
    suspend fun logOutCurrentUser()
}