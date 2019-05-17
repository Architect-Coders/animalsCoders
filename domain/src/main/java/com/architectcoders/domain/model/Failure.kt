package com.architectcoders.domain.model

data class Failure(val reason: Reason,
                   val message: String? = null) {

    enum class Reason {
        BLANK_INVALID_USER,
        BLANK_INVALID_PASS,
        USER_NOT_EXIST,
        CONNECTION_ISSUES
    }

}