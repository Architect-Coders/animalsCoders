package com.architectcoders.animalcoders.tools

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.architectcoders.animalcoders.R
import com.architectcoders.animalcoders.enums.AnimationsActivities

fun postDelayed(delayMillis: Long, task: () -> Unit) {
    Handler().postDelayed(task, delayMillis)
}

fun View.show() = run { visibility = View.VISIBLE }
fun View.hide() = run { visibility = View.GONE }

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun Activity.hideKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val token = findViewById<View>(android.R.id.content).rootView.windowToken

    imm.hideSoftInputFromWindow(token, 0)
}

fun isValidEmail(email: String): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}

inline fun <reified T: Activity> Activity.goToActivity(clear: Boolean = true,
                                                       animation: AnimationsActivities = AnimationsActivities.SLIDE_HORIZONTAL,
                                                       init: Intent.() -> Unit = {}) {

    val intent = Intent(this, T::class.java)
    if (clear) {
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    intent.init()
    startActivity(intent)

    when (animation) {
        AnimationsActivities.FADE -> overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        AnimationsActivities.SLIDE_HORIZONTAL -> overridePendingTransition(R.anim.right_in, R.anim.left_out)
        AnimationsActivities.SLIDE_VERTICAL -> overridePendingTransition(R.anim.slide_down, R.anim.slide_up)
        AnimationsActivities.ZOOM -> overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out)
        AnimationsActivities.SCALE -> overridePendingTransition(R.anim.scale_in, R.anim.scale_out)
        AnimationsActivities.NONE -> {}
    }
}

inline fun <reified T : Activity> Activity.goToActivityResult(requestCode: Int,
                                                              clear: Boolean = true,
                                                              animation: AnimationsActivities = AnimationsActivities.SLIDE_HORIZONTAL,
                                                              init: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    if (clear) {
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    intent.init()
    startActivityForResult(intent, requestCode)

    when (animation) {
        AnimationsActivities.FADE -> overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        AnimationsActivities.SLIDE_HORIZONTAL -> overridePendingTransition(R.anim.right_in, R.anim.left_out)
        AnimationsActivities.SLIDE_VERTICAL -> overridePendingTransition(R.anim.slide_down, R.anim.slide_up)
        AnimationsActivities.ZOOM -> overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out)
        AnimationsActivities.SCALE -> overridePendingTransition(R.anim.scale_in, R.anim.scale_out)
        AnimationsActivities.NONE -> {}
    }
}