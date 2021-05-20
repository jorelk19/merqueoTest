package com.merqueo.repository.local.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CartDTO : RealmObject() {
    @PrimaryKey
    private var _id: Int = 0
    private var _movies: RealmList<MovieCartItemDTO> = RealmList()
    private var _state : String = ""

    var id: Int
        get() {
            return _id
        }
        set(value) {
            _id = value
        }

    var movies: RealmList<MovieCartItemDTO>
        get() {
            return _movies
        }
        set(value) {
            _movies = value
        }

    var state: String
        get() {
            return _state
        }
        set(value) {
            _state = value
        }
}