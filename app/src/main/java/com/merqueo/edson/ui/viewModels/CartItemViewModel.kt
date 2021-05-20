package com.merqueo.edson.ui.viewModels

import androidx.lifecycle.MutableLiveData
import com.merqueo.businessmodels.business.Movie
import com.merqueo.domain.cart.CartDomain
import com.merqueo.edson.ui.viewModels.base.BaseViewModel

class CartItemViewModel(private val cartDomain: CartDomain) : BaseViewModel() {
    private lateinit var currentMovie: Movie
    var movieImage = MutableLiveData<String>()
    var movieName = MutableLiveData<String>()
    var movieQuantity = MutableLiveData<String>()

    fun setCartItemData(model: Movie) {
        currentMovie = model
        model.title?.let {
            this.movieName.value = it
        }
        model.posterPath?.let {
            this.movieImage.value = it
        }
        movieQuantity.value = model.quantity.toString()
    }

    fun onRemoveFromCart(){
        cartDomain.removeFromCart(currentMovie)
    }

    fun onAddToCart(){
        cartDomain.addToCart(currentMovie)
    }
}