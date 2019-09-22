package com.example.baseandroid.click

import android.os.SystemClock
import android.view.View

class SafeClickListener(
    private var defaultClickInterval: Int = 1000,
    private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {

    private var lastTimeClicked: Long = 0
    override fun onClick(v: View) {
        val timeElapsed = SystemClock.elapsedRealtime() - lastTimeClicked
        if (timeElapsed < defaultClickInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()

        onSafeCLick(v)
    }
}

fun View?.setSafeOnClickListener(onSafeClick: (View) -> Unit) = this?.run {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}
