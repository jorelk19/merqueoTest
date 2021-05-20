package com.merqueo.edson.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.merqueo.businessmodels.result.IMovieResult
import com.merqueo.domain.base.DomainManager
import com.merqueo.edson.ui.viewModels.mocks.MockMovieDomain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MovieViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var domainManager: DomainManager<IMovieResult>

    @Before
    fun setup() {
        domainManager = MockMovieDomain()
        movieViewModel = MovieViewModel(domainManager)
    }

    @Test
    fun getMoviesReturnElements() {
        movieViewModel.getMovies()

        Thread.sleep(3000)

        assert(movieViewModel.getMoviesLiveData().value?.size == 2)
    }

    @Test
    fun getMoviesAndValidateElements() {
        movieViewModel.getMovies()

        Thread.sleep(3000)

        assert(movieViewModel.getMoviesLiveData().value?.first()?.title == "movie1")
    }
}