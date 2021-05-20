package com.merqueo.edson.ui.views.fragments.adapters

import android.content.Context
import com.merqueo.businessmodels.business.Movie
import com.merqueo.domain.MovieDomain
import com.merqueo.domain.cart.CartDomain
import com.merqueo.edson.R
import com.merqueo.edson.databinding.LayoutMovieItemBinding
import com.merqueo.edson.ui.viewModels.MovieItemViewModel
import com.merqueo.utils.GenericAdapter

/**
 * Class used to set the data for the recycler view that show the movie list
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class MoviesAdapter(context: Context, movies: ArrayList<Movie>, private val movieDomain: MovieDomain, private val cartDomain: CartDomain) : GenericAdapter<Movie, LayoutMovieItemBinding>(context, movies) {

    /**
     * Method to retrieve the layout for the item in recycler view
     * */
    override fun getLayoutResId(): Int {
        return R.layout.layout_movie_item
    }

    /**
     * Method that allow load information into view model
     * */
    override fun onBindData(model: Movie, position: Int, dataBinding: LayoutMovieItemBinding) {
        val movieItemViewModel = MovieItemViewModel(movieDomain, cartDomain)
        dataBinding.itemViewModel = movieItemViewModel
        movieItemViewModel.setMovieData(model)
    }
}