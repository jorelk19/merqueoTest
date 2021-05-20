package com.merqueo.utils.app

import android.app.Application
import androidx.lifecycle.LifecycleObserver

/**
 * Abstract class that contain the application instance and lifeCycle observer
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
abstract class BaseApplication : Application(), LifecycleObserver {

    abstract fun onAppStart()

    abstract fun onAppDestroy()

    /**
     * Method that allow capture when the application start
     * */
    override fun onCreate() {
        super.onCreate()
        onAppStart()
    }
}