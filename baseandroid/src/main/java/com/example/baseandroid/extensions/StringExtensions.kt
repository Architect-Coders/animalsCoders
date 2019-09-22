package com.example.baseandroid.extensions

import android.util.Patterns

fun String.isValidEmail(): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(this).matches()
}