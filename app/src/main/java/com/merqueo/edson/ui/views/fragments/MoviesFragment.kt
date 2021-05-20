package com.merqueo.edson.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.merqueo.edson.R
import com.merqueo.edson.databinding.LayoutMoviesFragmentBinding
import com.merqueo.edson.ui.utils.getViewModelFactory
import com.merqueo.edson.ui.viewModels.MovieViewModel
import com.merqueo.edson.ui.views.fragments.adapters.MoviesAdapter
import com.merqueo.utils.Navigation

/**
 * Class used to load the movie list
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class MoviesFragment : Fragment() {
    private var moviesAdapter: MoviesAdapter? = null
    private val viewModel by viewModels<MovieViewModel> { getViewModelFactory() }
    private lateinit var binding: LayoutMoviesFragmentBinding

    /**
     * Method to instantiate the view
     * */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_movies_fragment, container, false)
        loadMovies()
        setAdapters()
        addSubscriptions()
        return binding.root
    }

    /**
     * Method to set adapters and load the movie list
     * */
    private fun setAdapters() {
        moviesAdapter = MoviesAdapter(Navigation.getInstance.getCurrentActivity(), arrayListOf(), viewModel.movieDomain, viewModel.cartDomain)
        binding.rvMovies.adapter = moviesAdapter
        binding.rvMovies.layoutManager = LinearLayoutManager(Navigation.getInstance.getCurrentActivity())
    }

    /**
     * Method to load the movies from the view model
     * */
    private fun loadMovies() {
        viewModel.getMovies()
    }

    /**
     * Method to add subscriptions and load adapter information
     * */
    private fun addSubscriptions() {
        viewModel.getMoviesLiveData().observe(viewLifecycleOwner, Observer { movies ->
            moviesAdapter?.addItems(movies)
        })
    }
}