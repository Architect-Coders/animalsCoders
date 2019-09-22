package com.example.baseandroid.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import java.lang.ref.WeakReference

class NetworkHelper(context: Context) {

    val context: WeakReference<Context> = WeakReference(context)

    @SuppressLint("MissingPermission")
    fun isNetworkAvailable(): Boolean {
        val manager = context.get()?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager?
        val networkInfo = manager?.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

}