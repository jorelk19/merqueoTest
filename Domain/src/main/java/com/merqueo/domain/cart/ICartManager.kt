package com.merqueo.domain.cart

import com.merqueo.businessmodels.business.Cart
import com.merqueo.businessmodels.business.Movie

interface ICartManager {
    fun getIsInitialized() : Boolean
    fun getCurrentCart() : Cart
    fun updateCart(cartId : Int)
    fun addToCart(movie : Movie)
    fun createCart()
    fun removeToCart(movie : Movie)
    fun emptyCart()
    fun setStoredCart(cart : Cart)
}