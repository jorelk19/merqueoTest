package com.merqueo.domain.cart

import com.merqueo.businessmodels.business.Cart
import com.merqueo.businessmodels.business.Movie

class CartManager {
    private lateinit var currentCart : Cart
    var isInitialized : Boolean = false
    fun getCurrentCart() : Cart {
        return currentCart
    }

    fun updateCart(cartId : Int){
        val updatedCart = currentCart.copy(cartId = cartId)
        currentCart = updatedCart
    }

    fun addToCart(movie : Movie){
        createCart()

        val indexItem = currentCart.movies.indexOfFirst { it.id == movie.id }
        if(indexItem >= 0){
            val item = currentCart.movies[indexItem].copy(quantity = currentCart.movies[indexItem].quantity + 1)
            currentCart.movies[indexItem] = item
        }else{
            val newItem = movie.copy(quantity = 1)
            currentCart.movies.add(newItem)
        }
    }

    fun createCart() {
        if(!::currentCart.isInitialized)
        {
            isInitialized = true
            currentCart = Cart()
        }
    }

    fun removeToCart(movie : Movie){
        if(movie.quantity == 0) {
            currentCart.movies.removeIf { it.id == movie.id }
        }else{
            val index = currentCart.movies.indexOfFirst { it.id == movie.id }
            if(index != -1 ){
                currentCart.movies[index] = movie
            }
        }
    }

    fun emptyCart(){
        currentCart = Cart()
        isInitialized = false
    }

    fun setStoredCart(cart : Cart){
        isInitialized = true
        currentCart = cart
    }

    fun getMovieQuantity(movie: Movie): Int {
        currentCart.movies.find { it.id == movie.id }?.let {
            return it.quantity
        } ?: run {
            return 0
        }
    }
}