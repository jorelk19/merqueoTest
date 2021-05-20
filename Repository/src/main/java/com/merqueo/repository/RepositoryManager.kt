package com.merqueo.repository

import com.merqueo.businessmodels.business.Movie
import com.merqueo.businessmodels.api.MovieApi
import com.merqueo.repository.local.MovieLocalRepository
import com.merqueo.repository.local.entities.MovieDTO
import com.merqueo.repository.remote.RemoteRepository

/**
 * Class used to manage the repository data and return correct information
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
open class RepositoryManager(private val movieApi: MovieApi, private val apiKey: String) : IRepositoryManager {
    private val movieLocalRepository = MovieLocalRepository()
    private val remoteRepository = RemoteRepository(movieApi,apiKey)

    override suspend fun getRemoteMovies(): ArrayList<Movie> {
        return remoteRepository.getRemoteMovies()
    }

    override suspend fun getRemoteMovieDetail(movieId: Int): Movie {
        return remoteRepository.getRemoteMovieDetail(movieId)
    }

    override fun saveMovieLocal(movie: Movie){
        movieLocalRepository.create(movie)
    }

    override fun deleteAllMoviesLocal(){
        movieLocalRepository.removeAll(MovieDTO())
    }

    override fun getLocalMovies(): ArrayList<Movie> {
        return movieLocalRepository.getAll(MovieDTO())
    }

    override fun getLocalMovie(movieId : Int): Movie {
        return movieLocalRepository.read(MovieDTO(), "_movieId", movieId, "INTEGER")
    }
}