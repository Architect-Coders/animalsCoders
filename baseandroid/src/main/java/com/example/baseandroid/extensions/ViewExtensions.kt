package com.example.baseandroid.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


fun View.show() = run { visibility = View.VISIBLE }

fun View.hide() = run { visibility = View.GONE }

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}