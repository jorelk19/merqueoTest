package com.merqueo.domain.cart

import com.merqueo.businessmodels.business.Cart
import com.merqueo.businessmodels.business.Movie

class CartManager : ICartManager {
    private lateinit var currentCart : Cart
    private var isInitialized : Boolean = false
    override fun getCurrentCart() : Cart {
        return currentCart
    }

    override fun getIsInitialized() : Boolean{
        return isInitialized
    }

    override fun updateCart(cartId : Int){
        val updatedCart = currentCart.copy(cartId = cartId)
        currentCart = updatedCart
    }

    override fun addToCart(movie : Movie){
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

    override fun createCart() {
        if(!::currentCart.isInitialized)
        {
            isInitialized = true
            currentCart = Cart()
        }
    }

    override fun removeToCart(movie : Movie){
        if(movie.quantity == 0) {
            currentCart.movies.removeIf { it.id == movie.id }
        }else{
            val index = currentCart.movies.indexOfFirst { it.id == movie.id }
            if(index != -1 ){
                currentCart.movies[index] = movie
            }
        }
    }

    override fun emptyCart(){
        currentCart = Cart()
        isInitialized = false
    }

    override fun setStoredCart(cart : Cart){
        isInitialized = true
        currentCart = cart
    }
}