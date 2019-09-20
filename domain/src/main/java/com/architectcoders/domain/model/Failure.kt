package com.architectcoders.domain.model

data class Failure(val reason: Reason,
                   val message: String? = null) {

    enum class Reason {
        API_ERROR,
        BLANK_INVALID_USER,
        BLANK_INVALID_PASS,
        USER_NOT_EXIST,
        CONNECTION_ISSUES,
        REGISTER_ERROR
    }

}
