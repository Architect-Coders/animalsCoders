package com.architectcoders.animalcoders.data.remote.login

import com.architectcoders.animalcoders.data.model.FirebaseResponse

class FirebaseLoginServiceImpl : LoginService {

    //TODO: Hacer llamada real a Firebase
    override suspend fun login(username: String, password: String) = when (username) {
        "coders" -> FirebaseResponse("alvaro", "alvaroToken", true, null, false)
        "http" -> FirebaseResponse(null, null, false, null, true)
        else -> FirebaseResponse(null, null, false, "Contraseña incorrecta", false)
    }

}