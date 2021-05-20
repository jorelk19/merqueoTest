package com.merqueo.edson.ui.viewModels

import androidx.lifecycle.MutableLiveData
import com.merqueo.businessmodels.business.Movie
import com.merqueo.businessmodels.result.IMovieResult
import com.merqueo.domain.MovieDomain
import com.merqueo.domain.cart.CartDomain
import com.merqueo.edson.R
import com.merqueo.edson.ui.viewModels.base.BaseViewModel
import com.merqueo.edson.ui.views.fragments.MoviesDetailFragment
import com.merqueo.utils.Navigation

/**
 * Class used to manage the view model for the movie item in recycler view
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class MovieItemViewModel(private val movieDomain: MovieDomain, private val cartDomain: CartDomain) : BaseViewModel() {
    private lateinit var currentMovie: Movie
    var movieName = MutableLiveData<String>()
    var movieImage = MutableLiveData<String>()
    var movieDetail = MutableLiveData<Movie>()

    /**
     * Method used to call the fragment to show movie detail
     * */
    fun showMovieDetail() {
        movieDomain.getMovieDetail(currentMovie.id)
    }

    /**
     * Method used to set the movie data
     * */
    fun setMovieData(model: Movie) {
        currentMovie = model
        model.title?.let {
            this.movieName.value = it
        }
        model.posterPath?.let {
            this.movieImage.value = it
        }

        movieDomain.errorManager = this
        movieDomain.domainResult(movieResult)
    }

    private val movieResult = object : IMovieResult {
        override fun setMovieList(movieList: ArrayList<Movie>) {
        }

        override fun setMovieDetail(movie: Movie) {
            movieDetail.value = movie
            if (::currentMovie.isInitialized) {
                Navigation.getInstance.attachFragment(MoviesDetailFragment.getInstance(movieDetail.value!!), R.id.fragment_container)
            }
        }
    }

    fun onAddMovieToCart() {
        cartDomain.addToCart(currentMovie)
        //cartDomain.getCart()
    }
}