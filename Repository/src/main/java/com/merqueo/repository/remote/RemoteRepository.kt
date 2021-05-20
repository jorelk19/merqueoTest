package com.merqueo.repository.remote

import com.merqueo.businessmodels.api.MovieApi
import com.merqueo.businessmodels.business.Movie

/**
 * Class used to load remote movie information
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class RemoteRepository(private val movieApi: MovieApi, private val apiKey: String) {
    suspend fun getRemoteMovies(): ArrayList<Movie> {
        return movieApi.getMovies(apiKey = apiKey).movies
    }

    suspend fun getRemoteMovieDetail(movieId : Int): Movie {
        return movieApi.getMovieDetail(apiKey = apiKey,movieId = movieId.toString())
    }
}