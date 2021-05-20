package com.merqueo.edson.ui.viewModels.mocks

import com.merqueo.businessmodels.business.Movie
import com.merqueo.businessmodels.result.IMovieResult
import com.merqueo.domain.base.DomainManager

class MockMovieDomain : DomainManager<IMovieResult>() {
    override fun domainResult(movieResult: IMovieResult) {
        movieList.add(movie1)
        movieList.add(movie2)
        movieResult.setMovieList(movieList)
    }
}

var movie1 = Movie(title = "movie1")
var movie2 = Movie(title = "movie2")

var movieList = ArrayList<Movie>()

