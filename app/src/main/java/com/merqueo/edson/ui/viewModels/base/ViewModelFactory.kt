package com.merqueo.edson.ui.viewModels.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.merqueo.di.KoinManager
import com.merqueo.edson.ui.viewModels.CartViewViewModel
import com.merqueo.edson.ui.viewModels.MainViewModel
import com.merqueo.edson.ui.viewModels.MovieDetailViewModel
import com.merqueo.edson.ui.viewModels.MovieViewModel

/**
 * Class used to create view models through factory pattern
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    /**
     * Method to return instance from specific view model
     * */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass)
        {
            when {
                isAssignableFrom(MainViewModel::class.java) -> MainViewModel(KoinManager.getAppComponent().cartDomain)
                isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel(KoinManager.getAppComponent().movieDomain, KoinManager.getAppComponent().cartDomain)
                isAssignableFrom(MovieDetailViewModel::class.java) -> MovieDetailViewModel()
                isAssignableFrom(CartViewViewModel::class.java) -> CartViewViewModel(KoinManager.getAppComponent().cartDomain)
                else -> throw IllegalStateException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}