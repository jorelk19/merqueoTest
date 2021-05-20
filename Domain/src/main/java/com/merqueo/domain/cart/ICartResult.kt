package com.merqueo.domain.cart

import com.merqueo.businessmodels.business.Cart

interface ICartResult {
    fun getCart(cart: Cart)
    fun updateCart(cart: Cart)
    fun removeFromCart(cart: Cart)
    fun emptyCart()
}