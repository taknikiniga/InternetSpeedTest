package com.taknikiniga.internetspeedtest.connection_monitor

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ListenConnectivity @Inject constructor(connectivityManager: ConnectivityManager) {

    /**
     * this [ isConnected callback ] simply return either internet is connected or not
     * and as we can see this is flow callback we can later on collect the data for the ui
     * changes
     */


    val isConnected = callbackFlow<Boolean> {

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                trySend(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                trySend(false)
            }
        }


        /**
         * Basically we want to listen internet connecitivity when ever user turn
         * on their cellular data or wifi
         */

        val request =
            NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                    }
                }.addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).build()

        trySend(MonitorConnection.isConnected(connectivityManager))

        /**
         * here we register our callback with network call back
         */

        connectivityManager.registerNetworkCallback(request, callback)

        /**
         * here we unregister our callback with network callback
         * because this is important to unregister the network callback
         */
        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }
}