package com.merqueo.repository.local.entities

import io.realm.RealmObject

open class MovieCartItemDTO : RealmObject() {
    private var _id: Int = 0
    private var _cartId: Int = 0
    private var _quantity: Int = 0

    var id: Int
        get() {
            return _id
        }
        set(value) {
            _id = value
        }

    var cartId: Int
        get() {
            return _cartId
        }
        set(value) {
            _cartId = value
        }

    var quantity: Int
        get() {
            return _quantity
        }
        set(value) {
            _quantity = value
        }
}