package com.merqueo.businessmodels.business

import com.google.gson.annotations.SerializedName

data class Movie (
    val id: Int = 0,
    val adult : Boolean = false,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null,
    @SerializedName("media_type")
    val mediaType: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity : Float = 0.0F,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    val title: String? = null,
    val video : Boolean = false,
    @SerializedName("vote_average")
    val voteAverage : Float = 0.0F,
    @SerializedName("vote_count")
    val voteCount : Int = 0,
    val quantity : Int = 0
)