package com.merqueo.repository.local

import com.merqueo.businessmodels.business.Movie
import com.merqueo.repository.local.entities.MovieDTO
import com.merqueo.repository.local.mapper.LocalRepositoryMovieMapper
import io.realm.Realm

/**
 * Class used to manage the local repository
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class MovieLocalRepository : ILocalRepositoryManager<Movie, MovieDTO> {
    private var realm: Realm = Realm.getDefaultInstance()
    override fun update(element: Movie) {
    }

    override fun create(movie: Movie): Int {
        val index = realm.where(MovieDTO::class.java).max("_id")
        val movieDto = LocalRepositoryMovieMapper.mapMovieDTO(movie)
        index?.let { num ->
            movieDto.id = num.toInt() + 1
        } ?: run { movieDto.id = 1 }

        realm.executeTransaction { currentRealm ->
            currentRealm.copyToRealm(movieDto)
        }

        return movieDto.id
    }

    override fun delete(element: Movie) {
    }

    override fun read(element: MovieDTO, fieldName: String, fieldValue: Any, castType: String): Movie {
        val movieDto = when (castType) {
            "INTEGER" -> realm.where(MovieDTO::class.java).equalTo(fieldName, fieldValue as Int).findFirst()
            "STRING" -> realm.where(MovieDTO::class.java).equalTo(fieldName, fieldValue.toString()).findFirst()
            else -> realm.where(MovieDTO::class.java).equalTo(fieldName, fieldValue.toString()).findFirst()
        }
        var movie = Movie()

        movieDto?.let { movieDtoResult ->
            movie = LocalRepositoryMovieMapper.mapMovieBusiness(movieDtoResult)
        } ?: run {
            movie = Movie(id = -1)
        }

        return movie
    }

    override fun removeAll(movieDTO: MovieDTO) {
        val currentMovieDTO = realm.where(MovieDTO::class.java).findAll()
        realm.executeTransaction {
            currentMovieDTO.deleteAllFromRealm()
        }
    }

    override fun getAll(element: MovieDTO): ArrayList<Movie> {
        val movies = ArrayList<Movie>()
        val moviesDto = realm.where(MovieDTO::class.java).findAll()
        moviesDto?.let { movieDTOResults ->
            movieDTOResults.forEach {
                movies.add(LocalRepositoryMovieMapper.mapMovieBusiness(it))
            }
        }
        return movies
    }
}

