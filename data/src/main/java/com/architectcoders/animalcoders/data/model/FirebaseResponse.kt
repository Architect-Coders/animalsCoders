package com.architectcoders.animalcoders.data.model

import arrow.core.Either
import com.architectcoders.domain.model.Failure
import com.architectcoders.domain.model.Failure.Reason
import com.architectcoders.domain.model.User

data class FirebaseResponse(
    val email: String?,
    val token: String?,
    val exist: Boolean,
    val errorMessage: String?,
    val httpError: Boolean
)

fun FirebaseResponse.mapper() =
    when {
        httpError -> Either.left(Failure(Reason.CONNECTION_ISSUES))
        !exist -> Either.left(Failure(Reason.USER_NOT_EXIST, errorMessage))
        email == null || token == null -> Either.left(Failure(Reason.CONNECTION_ISSUES))
        else -> Either.right(User(email, token))
    }
