package com.merqueo.edson.ui.viewModels

import com.merqueo.edson.ui.viewModels.mocks.movieDetail1
import org.junit.Before
import org.junit.Test

class MovieDetailViewModelTest {

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    @Before
    fun setup() {
        movieDetailViewModel = MovieDetailViewModel()
    }

    @Test
    fun validateSetDetailValues() {
        movieDetailViewModel.setMovieData(movieDetail1)

        assert(movieDetailViewModel.movieModel.movieDate == "23-02-2021")
    }
}