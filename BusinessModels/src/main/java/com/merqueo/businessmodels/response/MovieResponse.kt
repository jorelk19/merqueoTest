package com.merqueo.businessmodels.response

import com.merqueo.businessmodels.business.Movie
import com.google.gson.annotations.SerializedName

/**
 * Class used to get the movies response from the service
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
data class MovieResponse(
    @SerializedName("items")
    val movies : ArrayList<Movie> = arrayListOf()
)
