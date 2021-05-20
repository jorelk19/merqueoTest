package com.merqueo.di

import com.merqueo.di.KoinManager.Companion.cartManager
import com.merqueo.domain.MovieDomain
import com.merqueo.domain.cart.CartDomain
import com.merqueo.repository.RepositoryManager
import com.merqueo.repository.local.CartLocalRepository
import com.merqueo.repository.local.MovieLocalRepository
import org.koin.dsl.module

/**
 * Variable used to load the domain modules that can be used in the application
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
val domainModule = module {
    single { provideMovieDomain(get()) }
    single { provideCartDomain(get(), get()) }
}

private fun provideMovieDomain(repositoryManager: RepositoryManager) = MovieDomain(repositoryManager)
private fun provideCartDomain(cartLocalRepository: CartLocalRepository, movieLocalRepository: MovieLocalRepository) = CartDomain(cartLocalRepository, movieLocalRepository, cartManager)


