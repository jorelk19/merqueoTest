package com.merqueo.di

import com.merqueo.domain.MovieDomain
import com.merqueo.domain.cart.CartDomain
import com.merqueo.repository.RepositoryManager
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Class used to manage the koin component that can be used in the application
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class MovieComponent : KoinComponent {
    private val repositoryManager : RepositoryManager by inject()
    private val movieDomain : MovieDomain by inject()
    private val cartDomain : CartDomain by inject()
    val appComponent = AppComponent (
        repositoryManager = repositoryManager,
        movieDomain = movieDomain,
        cartDomain = cartDomain
    )
}
