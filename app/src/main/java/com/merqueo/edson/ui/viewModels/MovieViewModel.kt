package com.merqueo.edson.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.merqueo.businessmodels.business.Movie
import com.merqueo.businessmodels.result.IMovieResult
import com.merqueo.domain.MovieDomain
import com.merqueo.domain.cart.CartDomain
import com.merqueo.edson.ui.viewModels.base.BaseViewModel
import com.merqueo.utils.Navigation

class MovieViewModel(val movieDomain: MovieDomain, val cartDomain: CartDomain) : BaseViewModel() {

    var movies = MutableLiveData<ArrayList<Movie>>()
    fun getMoviesLiveData(): LiveData<ArrayList<Movie>> {
        return movies
    }

    private val movieResult = object : IMovieResult {
        override fun setMovieList(movieList: ArrayList<Movie>) {
            movies.value = movieList
        }

        override fun setMovieDetail(movie: Movie) {
        }
    }

    fun getMovies() {
        movieDomain.errorManager = this
        movieDomain.hasInternet = Navigation.getInstance.hasInternet()
        movieDomain.domainResult(movieResult)
        movieDomain.getMovies()
    }
}