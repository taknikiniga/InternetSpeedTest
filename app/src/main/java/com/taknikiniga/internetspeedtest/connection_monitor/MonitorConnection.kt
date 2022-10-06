package com.taknikiniga.internetspeedtest.connection_monitor

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

object MonitorConnection {

    private val IMPL = when{
        Build.VERSION.SDK_INT>=Build.VERSION_CODES.M ->{
            MarshmellowAndAbove
        }
        else ->{
            OtherVersion
        }
    }

    fun isConnected(connectivityManager: ConnectivityManager) : Boolean{
        return IMPL.isConnected(connectivityManager)
    }

}

interface NetworkCompat{
    fun isConnected(connectivityManager: ConnectivityManager): Boolean
}

/**
 * In This function we are checking internet connection for Marshmellow and above version
 */
object MarshmellowAndAbove : NetworkCompat {


    @RequiresApi(Build.VERSION_CODES.M)
    override fun isConnected(connectivityManager: ConnectivityManager): Boolean {
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

}

/**
 * In This function we are checking internet connection for below version from Marshmellow
 */
object OtherVersion : NetworkCompat {
    override fun isConnected(connectivityManager: ConnectivityManager): Boolean {
        return connectivityManager.activeNetworkInfo?.isConnected ?: false
    }

}