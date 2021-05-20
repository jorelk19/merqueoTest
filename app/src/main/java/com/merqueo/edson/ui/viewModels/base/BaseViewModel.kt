package com.merqueo.edson.ui.viewModels.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.merqueo.businessmodels.business.Cart
import com.merqueo.businessmodels.errors.IErrorManager
import com.merqueo.di.KoinManager.Companion.cartManager
import com.merqueo.domain.cart.ICartResult
import com.merqueo.edson.R
import com.merqueo.edson.ui.viewModels.CartViewViewModel
import com.merqueo.edson.ui.views.fragments.CartViewFragment
import com.merqueo.utils.Navigation
import com.merqueo.utils.SnackFactory
import com.merqueo.utils.loadImage
import retrofit2.HttpException

/**
 * This class in charge of manage the errors that can be occurred in the domain
 * when a services is executed
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
open class BaseViewModel : ViewModel(), IErrorManager, ICartResult {

    var currentCart : Cart = Cart()
    lateinit var cartView: CartViewFragment
    lateinit var viewModelCartItem: CartViewViewModel

    /**
     * Method to manage the http exception
     * */
    override fun onServiceErrorHttpException(error: String?, httpException: HttpException) {
        Navigation.getInstance.hideLoader()
        SnackFactory.showErrorMessage(httpException = httpException, resource = R.id.coordinator_main_activity, fragmentActivity = Navigation.getInstance.getCurrentActivity())
    }

    /**
     * Method to manage the socket time out exception
     * */
    override fun onSocketTimeoutException(error: String?) {
        Navigation.getInstance.hideLoader()
        SnackFactory.showWarningMessage(fragmentActivity = Navigation.getInstance.getCurrentActivity(), resource = R.id.coordinator_main_activity, message = error?.let { it } ?: run { Navigation.getInstance.getString(R.string.something_went_wrong_retry) })
    }

    /**
     * Method to manage the IO exception
     * */
    override fun onIOException(error: String?) {
        Navigation.getInstance.hideLoader()
        SnackFactory.showWarningMessage(fragmentActivity = Navigation.getInstance.getCurrentActivity(), resource = R.id.coordinator_main_activity, message = error?.let { it } ?: run { Navigation.getInstance.getString(R.string.something_went_wrong_retry) })
    }

    /**
     * Method to hide the loader
     * */
    override fun onHideLoader() {
        Navigation.getInstance.hideLoader()
    }

    /**
     * Method to show the loader
     * */
    override fun onShowLoader() {
        Navigation.getInstance.showLoader()
    }

    companion object {
        @BindingAdapter("android:src")
        @JvmStatic
        fun loadImage(imageView: ImageView, movieImage: String?) {
            movieImage?.let{
                imageView.loadImage(it)
            }
        }
    }

    override fun getCart(cart: Cart) {
        val movies = cart.movies
        if(!::cartView.isInitialized) {
            cartView = CartViewFragment.newInstance(cart.movies, viewModelCartItem)
        }
        cartView.setMovies(movies)
        currentCart = cart
        cartManager.setStoredCart(currentCart)
    }

    override fun updateCart(cart: Cart) {
        currentCart = cart
        //cartManager.setStoredCart(currentCart)
        cartView.setMovies(currentCart.movies)
    }

/*
    override fun createCart(success: Boolean) {
    }
*/

    override fun emptyCart() {
        currentCart = Cart()
        cartManager.setStoredCart(currentCart)
    }

    override fun removeFromCart(cart: Cart){
        currentCart = cart
        cartView.setMovies(currentCart.movies)
    }
}