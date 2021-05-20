package com.merqueo.repository.local.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Class used to store local information with realm
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
open class MovieDTO : RealmObject() {

    @PrimaryKey
    private var _id: Int = 0
    private var _movieId: Int = 0
    private var _adult: Boolean = false
    private var _backdropPath: String = ""
    private var _mediaType: String = ""
    private var _originalLanguage: String = ""
    private var _originalTitle: String = ""
    private var _overview: String = ""
    private var _popularity: Float = 0.0F
    private var _posterPath: String = ""
    private var _releaseDate: String = ""
    private var _title: String = ""
    private var _video: Boolean = false
    private var _voteAverage: Float = 0.0F
    private var _voteCount: Int = 0
    private var _quantity: Int = 0

    var id: Int
        get() {
            return _id
        }
        set(value) {
            _id = value
        }

    var movieId: Int
        get() {
            return _movieId
        }
        set(value) {
            _movieId = value
        }

    var adult: Boolean
        get() {
            return _adult
        }
        set(value) {
            _adult = value
        }

    var backdropPath: String
        get() {
            return _backdropPath
        }
        set(value) {
            _backdropPath = value
        }

    var mediaType: String
        get() {
            return _mediaType
        }
        set(value) {
            _mediaType = value
        }

    var originalLanguage: String
        get() {
            return _originalLanguage
        }
        set(value) {
            _originalLanguage = value
        }

    var originalTitle: String
        get() {
            return _originalTitle
        }
        set(value) {
            _originalTitle = value
        }

    var overview: String
        get() {
            return _overview
        }
        set(value) {
            _overview = value
        }

    var popularity: Float
        get() {
            return _popularity
        }
        set(value) {
            _popularity = value
        }

    var posterPath: String
        get() {
            return _posterPath
        }
        set(value) {
            _posterPath = value
        }

    var releaseDate: String
        get() {
            return _releaseDate
        }
        set(value) {
            _releaseDate = value
        }

    var title: String
        get() {
            return _title
        }
        set(value) {
            _title = value
        }

    var video: Boolean
        get() {
            return _video
        }
        set(value) {
            _video = value
        }

    var voteAverage: Float
        get() {
            return _voteAverage
        }
        set(value) {
            _voteAverage = value
        }

    var voteCount: Int
        get() {
            return _voteCount
        }
        set(value) {
            _voteCount = value
        }

    var quantity: Int
        get() {
            return _quantity
        }
        set(value) {
            _quantity = value
        }
}
