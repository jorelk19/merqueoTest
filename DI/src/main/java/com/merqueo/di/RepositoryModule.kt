package com.merqueo.di

import com.merqueo.businessmodels.api.MovieApi
import com.merqueo.businessmodels.result.IMovieResult
import com.merqueo.domain.IMovieDomain
import com.merqueo.repository.RepositoryManager
import com.merqueo.repository.local.CartLocalRepository
import com.merqueo.repository.local.MovieLocalRepository
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Variable used to load the repository modules that can be used in the application
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class RepositoryModule(private val apiKey: String) {
    /**
     * Provider to get the repository manager instance
     * */
    private fun provideRepositoryManager(movieApi: MovieApi) = RepositoryManager(movieApi, apiKey)
    private fun provideCartRepository() = CartLocalRepository()
    private fun provideMovieRepository() = MovieLocalRepository()

    fun initModule(): Module {
        return module {
            single { provideRepositoryManager(get()) }
            single { provideCartRepository() }
            single { provideMovieRepository() }
        }
    }
}


