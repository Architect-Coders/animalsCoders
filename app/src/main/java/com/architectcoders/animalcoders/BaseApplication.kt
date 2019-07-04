package com.architectcoders.animalcoders

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import io.fabric.sdk.android.Fabric

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
        setupCrashlytics()
    }

    private fun setupCrashlytics() {
        val core = CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build() // Para desactivarlo
        // val core = CrashlyticsCore.Builder().disabled(false).build() // Para activarlo
        val crashlytics = Crashlytics.Builder()
            .core(core)
            .build()
        Fabric.with(this, crashlytics)

    }
}
