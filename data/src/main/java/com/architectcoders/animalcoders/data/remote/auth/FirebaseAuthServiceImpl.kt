package com.architectcoders.animalcoders.data.remote.auth

import arrow.core.Either
import com.architectcoders.domain.model.Failure
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthServiceImpl(private var auth: FirebaseAuth) : AuthService {

    override suspend fun logOutCurrentUser() {
        auth.signOut()
    }

    override suspend fun getCurrentUser(): Either<Failure, FirebaseUser> {
        return auth.currentUser?.let { user ->
            Either.right(user)
        } ?: Either.left(Failure(Failure.Reason.USER_NOT_EXIST, null))
    }

}