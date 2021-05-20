package com.merqueo.edson.ui.viewModels

import com.merqueo.businessmodels.business.Cart
import com.merqueo.businessmodels.business.Movie
import com.merqueo.domain.cart.CartDomain
import com.merqueo.domain.cart.ICartResult
import com.merqueo.edson.R
import com.merqueo.edson.ui.viewModels.base.BaseViewModel
import com.merqueo.edson.ui.views.fragments.CartViewFragment
import com.merqueo.edson.ui.views.fragments.MoviesFragment
import com.merqueo.utils.Navigation

class MainViewModel(private val cartDomain: CartDomain) : BaseViewModel() {


    private lateinit var movies : ArrayList<Movie>

   /* private val cartResult = object : ICartResult {
        override fun getCart(cart: Cart) {
            movies = cart.movies
            if(!::cartView.isInitialized) {
                cartView = CartViewFragment.newInstance(cart.movies, viewModelCartItem)
            }
            cartView.setMovies(movies)
        }

        override fun updateCart(cart: Cart) {
*//*            movies = cart.movies
            cartView.setMovies(movies)*//*
        }

 *//*       override fun createCart(success: Boolean) {
        }
*//*
*//*        override fun setQuantity(quantity: Int) {
        }*//*

        override fun emptyCart() {
    *//*        movies.clear()
            cartView.setMovies(movies)*//*
        }
    }*/

    fun onShowCartView() {
        //this.cartView.setMovies(movies)
        Navigation.getInstance.attachFragment(this.cartView, R.id.fragment_container, addNewTransaction = false)
    }

    fun attachMovieFragment() {
        Navigation.getInstance.attachFragment(MoviesFragment(), R.id.fragment_container)
    }

    fun loadCurrentCart(vmCartItem: CartViewViewModel) {
        this.viewModelCartItem = vmCartItem
        cartDomain.errorManager = this
        cartDomain.domainResult(this)
        //cartDomain.domainResult(cartResult)
        cartDomain.getCart()
    }
}