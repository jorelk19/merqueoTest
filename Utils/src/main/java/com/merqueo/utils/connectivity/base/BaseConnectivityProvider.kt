package com.merqueo.utils.connectivity.base

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkInfo
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import com.merqueo.utils.connectivity.ConnectivityProvider
import com.merqueo.utils.connectivity.LegacyConnectivityProvider

abstract class BaseConnectivityProvider {
    private val handler = Handler(Looper.getMainLooper())
    private val listeners = mutableSetOf<ConnectivityStateListener>()
    private var subscribed = false

    fun addListener(listener: ConnectivityStateListener) {
        listeners.add(listener)
        listener.onStateChange(getNetworkState())
        verifySubscription()
    }

    protected fun removeListener(listener: ConnectivityStateListener) {
        listeners.remove(listener)
        verifySubscription()
    }

    private fun verifySubscription() {
        if (!subscribed && listeners.isNotEmpty()) {
            subscribe()
            subscribed = true
        } else if (subscribed && listeners.isEmpty()) {
            unsubscribe()
            subscribed = false
        }
    }

    protected fun dispatchChange(state: NetworkState) {
        handler.post {
            for (listener in listeners) {
                listener.onStateChange(state)
            }
        }
    }

    protected abstract fun subscribe()
    protected abstract fun unsubscribe()

    abstract fun getNetworkState(): NetworkState

    @Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")
    sealed class NetworkState {
        object NotConnectedState : NetworkState()

        sealed class ConnectedState(val hasInternet: Boolean) : NetworkState() {

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            data class Connected(val capabilities: NetworkCapabilities) : ConnectedState(
                capabilities.hasCapability(NET_CAPABILITY_INTERNET)
            )

            @Suppress("DEPRECATION")
            data class ConnectedLegacy(val networkInfo: NetworkInfo) : ConnectedState(
                networkInfo.isConnectedOrConnecting
            )
        }
    }

    companion object {
        fun createProvider(context: Context): BaseConnectivityProvider {
            val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                ConnectivityProvider(cm)
            } else {
                LegacyConnectivityProvider(context, cm)
            }
        }
    }

    interface ConnectivityStateListener {
        fun onStateChange(state: NetworkState)
    }

}