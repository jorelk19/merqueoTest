package com.merqueo.edson.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.merqueo.businessmodels.business.Cart
import com.merqueo.businessmodels.business.Movie
import com.merqueo.domain.cart.CartDomain
import com.merqueo.domain.cart.ICartResult
import com.merqueo.edson.ui.viewModels.base.BaseViewModel

class CartViewViewModel(val cartDomain: CartDomain) : BaseViewModel() {
    private val movieList = MutableLiveData<ArrayList<Movie>>()
    private val isEmptyCart = MutableLiveData<Boolean>()

    fun getMoviesLiveData(): LiveData<ArrayList<Movie>> {
        return movieList
    }

    fun getIsEmptyCart(): LiveData<Boolean> {
        return isEmptyCart
    }

    fun setCurrentCartItem(){
        isEmptyCart.value = movieList.value!!.isEmpty()
    }

    fun onEmptyCart(){
        cartDomain.emptyCart()
    }

    private val result = object : ICartResult{
        override fun getCart(cart: Cart) {
            movieList.value = cart.movies
        }

        override fun updateCart(cart: Cart) {
            movieList.value = cart.movies
        }

        override fun emptyCart() {
            movieList.value = arrayListOf()
            setCurrentCartItem()
        }

        override fun removeFromCart(cart: Cart){
            movieList.value = cart.movies
            setCurrentCartItem()
        }
    }

    fun setCurrentMovies(movies: ArrayList<Movie>) {
        movieList.value = movies
        cartDomain.domainResult(result)
    }
}