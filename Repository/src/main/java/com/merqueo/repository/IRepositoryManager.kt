package com.merqueo.repository

import com.merqueo.businessmodels.business.Movie

interface IRepositoryManager {
    suspend fun getRemoteMovies(): ArrayList<Movie>
    suspend fun getRemoteMovieDetail(movieId: Int): Movie
    fun saveMovieLocal(movie: Movie)
    fun deleteAllMoviesLocal()
    fun getLocalMovies(): ArrayList<Movie>
    fun getLocalMovie(movieId : Int): Movie
}