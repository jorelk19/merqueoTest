import com.merqueo.businessmodels.business.Cart
import com.merqueo.businessmodels.business.Movie
import com.merqueo.businessmodels.errors.IErrorManager
import com.merqueo.domain.cart.ICartManager
import com.merqueo.repository.local.ILocalRepositoryManager
import com.merqueo.repository.local.entities.CartDTO
import com.merqueo.repository.local.entities.MovieDTO
import retrofit2.HttpException

var mockCartRepositoryManager = object : ILocalRepositoryManager<Cart, CartDTO>{
    override fun update(element: Cart) {
    }

    override fun create(element: Cart): Int {
        return 1
    }

    override fun delete(element: Cart) {
    }

    override fun read(element: CartDTO, fieldName: String, fieldValue: Any, castType: String): Cart {
        return cart
    }

    override fun removeAll(element: CartDTO) {
    }

    override fun getAll(element: CartDTO): ArrayList<Cart> {
        return cartList
    }

}
var movie = Movie(id = 123456, backdropPath = "/test.jpg", quantity = 5)
var newMovie = Movie(id = 123, backdropPath = "/test123.jpg", quantity = 2)
var newMovie1 = Movie(id = 1234, backdropPath = "/test123.jpg", quantity = 2)
var movieList = arrayListOf(movie)
var completedList = arrayListOf(movie, newMovie, newMovie1)
var cart = Cart(cartId = 1, state = "OPEN", movies = movieList )
var newCart = Cart(cartId = 1, state = "OPEN", movies = completedList )
var cartList = arrayListOf(cart)



var cartUpdated = Cart()

var cartManagerMock = object : ICartManager{
    override fun getIsInitialized(): Boolean {
        return true
    }

    override fun getCurrentCart(): Cart {
        return cart
    }

    override fun updateCart(cartId: Int) {
        cartUpdated = cart.copy(cartId = cartId)
    }

    override fun addToCart(movie: Movie) {
        movieList.add(movie)
    }

    override fun createCart() {
        cart = Cart()
    }

    override fun removeToCart(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun emptyCart() {
        cart = Cart()
    }

    override fun setStoredCart(newCart: Cart) {
        cart = newCart
    }

}


var mockMovieRepositoryManager = object : ILocalRepositoryManager<Movie, MovieDTO>{
    override fun update(element: Movie) {

    }

    override fun create(element: Movie): Int {
        return 2
    }

    override fun delete(element: Movie) {
    }

    override fun read(element: MovieDTO, fieldName: String, fieldValue: Any, castType: String): Movie {
        return movie
    }

    override fun removeAll(element: MovieDTO) {
    }

    override fun getAll(element: MovieDTO): ArrayList<Movie> {
        return movieList
    }
}


/**Error manager*/
open class ErrorManagerMock : IErrorManager {
    override fun onServiceErrorHttpException(error: String?, httpException: HttpException) {

    }

    override fun onSocketTimeoutException(error: String?) {

    }

    override fun onIOException(error: String?) {

    }

    override fun onHideLoader() {

    }

    override fun onShowLoader() {

    }

}