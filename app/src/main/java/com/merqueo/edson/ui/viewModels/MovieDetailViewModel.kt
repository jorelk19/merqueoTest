package com.merqueo.edson.ui.viewModels

import androidx.lifecycle.MutableLiveData
import com.merqueo.businessmodels.business.Movie
import com.merqueo.edson.ui.viewModels.base.BaseViewModel

/**
 * Class used to manage the view model for the movie detail view
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class MovieDetailViewModel : BaseViewModel() {
    var movieDate = MutableLiveData<String>()
    var movieName = MutableLiveData<String>()
    var movieOverview = MutableLiveData<String>()
    var movieImage = MutableLiveData<String>()
    /**
     * Method to set the movie values
     **/
    fun setMovieData(movie: Movie) {
        this.movieDate.value = movie.releaseDate.toString()
        this.movieName.value = movie.title?.let { it } ?: run { "" }
        this.movieOverview.value = movie.overview?.let { it } ?: run { "" }
        this.movieImage.value = movie.posterPath?.let { it } ?: run { "" }
    }
}