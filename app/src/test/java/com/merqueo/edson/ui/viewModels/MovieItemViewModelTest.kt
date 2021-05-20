package com.merqueo.edson.ui.viewModels

import com.merqueo.businessmodels.business.Movie
import org.junit.Before
import org.junit.Test

class MovieItemViewModelTest {
    private lateinit var movieItemViewModel: MovieItemViewModel

    @Before
    fun setup() {
        movieItemViewModel = MovieItemViewModel()
    }


    @Test
    fun validateSetViewModelProperties() {
        movieItemViewModel.setMovieData(Movie(title = null, posterPath = null))

        assert(movieItemViewModel.movieItemModel.movieName == "")
    }
}