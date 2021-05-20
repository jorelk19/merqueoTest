package com.merqueo.di

import android.content.Context
import com.merqueo.domain.cart.CartManager
import com.merqueo.repository.RepositoryConfiguration
import org.koin.core.context.startKoin

/**
 * Class used to manage the koin dependency injection that load different modules in the application
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class KoinManager {
    companion object {
        fun initKoin(movieApi: String, apiKey: String) {
            startKoin {
                modules(
                    arrayListOf(
                        RepositoryModule(apiKey).initModule(),
                        NetworkModule(movieApi).initModule(),
                        domainModule
                    )
                )
            }
        }

        fun getAppComponent() : AppComponent{
            val appComponent by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { MovieComponent().appComponent }
            return appComponent
        }

        fun startRepositoryRealm(context: Context){
            RepositoryConfiguration.startRealm(context)
        }

        val cartManager  by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { CartManager() }
    }
}