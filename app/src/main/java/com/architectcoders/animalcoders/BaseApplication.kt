package com.architectcoders.animalcoders

import android.app.Application
import arrow.core.Either
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import io.fabric.sdk.android.Fabric

class BaseApplication: Application() {

    lateinit var instance: BaseApplication
    private lateinit var db: FirebaseFirestore
    override fun onCreate() {
        super.onCreate()

        instance = this

        // Configuro Crashlytics (en desarrollo lo deshabilito)
        setupCrashlytics()
        // Configuro Firestore Cloud
        setupFB()
    }

    private fun setupCrashlytics() {
        val core = CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build() // Para desactivarlo
        // val core = CrashlyticsCore.Builder().disabled(false).build() // Para activarlo
        val crashlytics = Crashlytics.Builder()
            .core(core)
            .build()
        Fabric.with(this, crashlytics)

        Either
    }

    private fun setupFB() {

        FirebaseApp.initializeApp(this)
        db = FirebaseFirestore.getInstance()

        // Activo la persistencia (En Android no es necesario, viene activada por defecto)
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        db.firestoreSettings = settings

        // Activo el log
        // FirebaseFirestore.setLoggingEnabled(true)
    }
}