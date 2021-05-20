package com.merqueo.businessmodels.result

import com.merqueo.businessmodels.business.Movie

interface IMovieResult {
    fun setMovieList(movieList: ArrayList<Movie>)
    fun setMovieDetail(movie: Movie)
}