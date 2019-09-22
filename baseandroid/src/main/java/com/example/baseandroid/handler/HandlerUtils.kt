package com.example.baseandroid.handler

import android.os.Handler

object HandlerUtils {

    fun postDelayed(delayMillis: Long, task: () -> Unit) {
        Handler().postDelayed(task, delayMillis)
    }
}