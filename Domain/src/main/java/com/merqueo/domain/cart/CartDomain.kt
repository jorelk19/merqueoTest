package com.merqueo.domain.cart

import com.merqueo.businessmodels.business.Cart
import com.merqueo.businessmodels.business.Movie
import com.merqueo.domain.base.DomainManager
import com.merqueo.repository.local.ILocalRepositoryManager
import com.merqueo.repository.local.entities.CartDTO
import com.merqueo.repository.local.entities.MovieDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

class CartDomain(private val cartRepositoryManager: ILocalRepositoryManager<Cart, CartDTO>, private val movieRepositoryManager: ILocalRepositoryManager<Movie, MovieDTO>, private val cartManager: ICartManager) : DomainManager<ICartResult>() {
    private lateinit var currentCartResult: ICartResult
    override fun domainResult(cartResult: ICartResult) {
        currentCartResult = cartResult
    }

    fun addToCart(movie: Movie) {
        launch(Dispatchers.Main) {
            try {
                errorManager.onShowLoader()
                if (!cartManager.getIsInitialized()) {
                    cartManager.createCart()
                }
                if (cartManager.getCurrentCart().cartId == 0) {
                    val newId = cartRepositoryManager.create(cartManager.getCurrentCart())
                    cartManager.updateCart(newId)
                }
                cartManager.addToCart(movie)
                val completedMovieData = getCompletedMovieList(cartManager.getCurrentCart())
                val completedCart = cartManager.getCurrentCart().copy(movies = completedMovieData)
                cartRepositoryManager.update(completedCart)
                currentCartResult.updateCart(completedCart)
                cartManager.setStoredCart(completedCart)
                errorManager.onHideLoader()
            } catch (exception: IOException) {
                errorManager.onSocketTimeoutException(exception.message)
                errorManager.onHideLoader()
            } catch (exception : Exception){
                errorManager.onSocketTimeoutException(exception.message)
                errorManager.onHideLoader()
            }
        }
    }

    fun removeFromCart(movie: Movie) {
        launch(Dispatchers.Main) {
            try {
                errorManager.onShowLoader()
                val cart = cartRepositoryManager.read(CartDTO(), "_id", cartManager.getCurrentCart().cartId, "INTEGER")
                val completedMovieData = getCompletedMovieList(cart)
                val completedCart = cart.copy(movies = completedMovieData)
                val indexItem = completedCart.movies.indexOfFirst { it.id == movie.id }
                if (indexItem >= 0) {
                    if (completedCart.movies[indexItem].quantity >= 0) {
                        val item = completedCart.movies[indexItem].copy(quantity = completedCart.movies[indexItem].quantity - 1)
                        completedCart.movies[indexItem] = item
                        cartManager.setStoredCart(completedCart)
                        cartManager.removeToCart(completedCart.movies[indexItem])
                        cartRepositoryManager.update(completedCart)
                    }
                }
                currentCartResult.removeFromCart(cartManager.getCurrentCart())
                errorManager.onHideLoader()
            } catch (exception: IOException) {
                errorManager.onSocketTimeoutException(exception.message)
                errorManager.onHideLoader()
                errorManager.onHideLoader()
            } catch (exception : Exception){
                errorManager.onSocketTimeoutException(exception.message)
                errorManager.onHideLoader()
            }
        }
    }

    fun emptyCart() {
        launch(Dispatchers.Main) {
            try {
                errorManager.onShowLoader()
                val currentCart = cartManager.getCurrentCart().copy(state = "CLOSED")
                cartRepositoryManager.update(currentCart)
                cartManager.emptyCart()
                currentCartResult.emptyCart()
                errorManager.onHideLoader()
            } catch (exception: IOException) {
                errorManager.onSocketTimeoutException(exception.message)
                errorManager.onHideLoader()
            } catch (exception : Exception){
                errorManager.onSocketTimeoutException(exception.message)
                errorManager.onHideLoader()
            }
        }
    }

    fun getCart() {
        launch(Dispatchers.Main) {
            try {
                errorManager.onShowLoader()
                val cart = cartRepositoryManager.read(CartDTO(), "_state", "OPEN", "STRING")
                if (cart.cartId == -1) {
                    val newCart = Cart(state = "OPEN")
                    cartManager.setStoredCart(newCart)
                    val cartId = cartRepositoryManager.create(newCart)
                    currentCartResult.getCart(newCart.copy(cartId = cartId))
                } else {
                    val completedMovieData = getCompletedMovieList(cart)
                    val completedCart = cart.copy(movies = completedMovieData)
                    cartManager.setStoredCart(completedCart)
                    currentCartResult.getCart(completedCart)
                }
                errorManager.onHideLoader()
            } catch (exception: IOException) {
                errorManager.onSocketTimeoutException(exception.message)
                errorManager.onHideLoader()
            } catch (exception : Exception){
                errorManager.onSocketTimeoutException(exception.message)
                errorManager.onHideLoader()
            }
        }
    }

    fun getCompletedMovieList(cart: Cart): ArrayList<Movie> {
        val completedMovieData = ArrayList<Movie>()
        cart.movies.forEach { movie ->
            val movieDTO = MovieDTO()
            movieDTO.movieId = movie.id
            val completedMovie = movieRepositoryManager.read(movieDTO, "_movieId", movie.id, "INTEGER")
            completedMovieData.add(completedMovie.copy(quantity = movie.quantity))
        }
        return completedMovieData
    }
}