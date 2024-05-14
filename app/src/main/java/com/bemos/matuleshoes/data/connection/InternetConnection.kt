package com.bemos.matuleshoes.data.connection

import android.app.usage.NetworkStats
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo

fun checkInternet(context: Context): Boolean {
    val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (connectivity != null) {
        val info = connectivity.allNetworkInfo
        if (info != null) {
            info.forEach {
                if (it.state == NetworkInfo.State.CONNECTED){
                    return true
                }
            }
        }
    }
    return false
}