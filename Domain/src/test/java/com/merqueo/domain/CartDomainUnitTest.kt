package com.merqueo.domain

import ErrorManagerMock
import cart
import cartManagerMock
import cartUpdated
import com.merqueo.businessmodels.business.Cart
import com.merqueo.businessmodels.business.Movie
import com.merqueo.domain.cart.CartDomain
import com.merqueo.domain.cart.CartManager
import com.merqueo.domain.cart.ICartManager
import com.merqueo.domain.cart.ICartResult
import com.merqueo.repository.local.ILocalRepositoryManager
import com.merqueo.repository.local.entities.CartDTO
import com.merqueo.repository.local.entities.MovieDTO
import com.nhaarman.mockitokotlin2.mock
import completedList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import mockCartRepositoryManager
import mockMovieRepositoryManager
import movie
import newCart
import newMovie
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.powermock.api.mockito.PowerMockito
import org.powermock.api.mockito.PowerMockito.mock

class CartDomainUnitTest {

    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var cartRepositoryManager: ILocalRepositoryManager<Cart, CartDTO>
    lateinit var movieRepositoryManager: ILocalRepositoryManager<Movie, MovieDTO>
    lateinit var cartDomain: CartDomain
    lateinit var cartManager: ICartManager
    lateinit var cartResult: ICartResult
    lateinit var errorManagerMock: ErrorManagerMock

    @Before
    fun setuUp(){
        cartRepositoryManager = mock()
        movieRepositoryManager = mock()
        cartManager = CartManager()
        cartResult = mock(ICartResult::class.java)
        cartDomain = CartDomain(cartRepositoryManager, movieRepositoryManager, cartManager)
        cartDomain.domainResult(cartResult)
        errorManagerMock = ErrorManagerMock()
        cartDomain.errorManager = errorManagerMock
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun resetCoroutines() {
        // Resets state of the [Dispatchers.Main] to the original main dispatcher.
        // For example, in Android Main thread dispatcher will be set as [Dispatchers.Main].
        Dispatchers.resetMain()

        // Clean up the TestCoroutineDispatcher to make sure no other work is running.
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun verifyAddMovieToCartWhenCartIsNew(){
        //WHEN
        cartManager.setStoredCart(cart)
        cartDomain.addToCart(newMovie)

        Thread.sleep(5000)
        //THEN
        Assert.assertTrue(cart.cartId == 1)
        Assert.assertTrue(cart.movies.size == 2)
        Assert.assertTrue(cart.movies.find { it.id == newMovie.id } != null )
    }

    @Test
    fun verifyRemoveFromCart(){
        //WHEN
        cartManager.setStoredCart(newCart)
        PowerMockito.`when`(cartRepositoryManager.read(CartDTO(), "_id", cartManager.getCurrentCart().cartId, "INTEGER")).thenReturn(cart)
        PowerMockito.`when`(movieRepositoryManager.read(MovieDTO(), "_movieId", movie.id, "INTEGER")).thenReturn(movie)
        //PowerMockito.`when`(cartDomain.getCompletedMovieList(cart)).getMock<ArrayList<Movie>>()
        cartDomain.removeFromCart(movie)

        Thread.sleep(5000)
        //THEN
        Assert.assertTrue(cart.cartId == 1)
        println(cartManager.getCurrentCart().movies.size)
        Assert.assertTrue(cartManager.getCurrentCart().movies.size == 2)
        Assert.assertTrue(cartManager.getCurrentCart().movies.find { it.id == movie.id } == null)
        Assert.assertTrue(cart.movies.find { it.id == movie.id } == null )
    }

}