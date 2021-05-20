package com.merqueo.utils.app

import android.content.Context
import com.merqueo.di.KoinManager
import com.merqueo.utils.Navigation
import com.merqueo.utils.connectivity.base.BaseConnectivityProvider
import com.merqueo.utils.R

/**
 * Class used to get the application instance and call the koin dependency injection
 * @since 1.0.0
 * */
open class App : BaseApplication() {
    private val providerBase: BaseConnectivityProvider by lazy {
        BaseConnectivityProvider.createProvider(
            this@App
        )
    }

    /**
     * Object to manage the internet connection
     * */

    private val connectivityStateListener =
        object : BaseConnectivityProvider.ConnectivityStateListener {
            override fun onStateChange(state: BaseConnectivityProvider.NetworkState) {
                Navigation.getInstance.setInternetConnection(state.hasInternet())
            }

            private fun BaseConnectivityProvider.NetworkState.hasInternet(): Boolean {
                return (this as? BaseConnectivityProvider.NetworkState.ConnectedState)?.hasInternet == true
            }
        }


    /**
     * Get the application context
     * */
    companion object {
        private lateinit var appContext: Context
        final fun getAppContext(): Context = appContext
    }

    /**
     * Method that allow start koin dependency injection
     * */
    override fun onAppStart() {
        appContext = this
        KoinManager.initKoin(App.getAppContext().getString(R.string.movies_api), getAppContext().getString(R.string.app_api_key))
        providerBase.addListener(connectivityStateListener)
        KoinManager.startRepositoryRealm(this)
    }

    /**
     * Method that is executed when the application is finish
     * */
    override fun onAppDestroy() {
    }
}