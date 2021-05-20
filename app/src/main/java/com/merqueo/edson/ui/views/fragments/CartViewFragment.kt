package com.merqueo.edson.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.merqueo.businessmodels.business.Movie
import com.merqueo.edson.R
import com.merqueo.edson.databinding.LayoutCartViewBinding
import com.merqueo.edson.ui.viewModels.CartViewViewModel
import com.merqueo.edson.ui.views.fragments.adapters.CartViewAdapter

class CartViewFragment : Fragment() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var cartViewAdapter: CartViewAdapter
    private lateinit var layoutCartViewBinding: LayoutCartViewBinding
    private lateinit var viewModel : CartViewViewModel


    companion object {
        fun newInstance(movieList: ArrayList<Movie>, viewModelCartItem: CartViewViewModel): CartViewFragment {
            val instance = CartViewFragment()
            instance.setElements(movieList, viewModelCartItem)
            return instance
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        layoutCartViewBinding = DataBindingUtil.inflate(inflater, R.layout.layout_cart_view, container, false)
        layoutCartViewBinding.viewModel = viewModel
        setLayoutManager()
        evaluateCartState()
        addSubscriptions()
        return layoutCartViewBinding.root
    }

    private fun addSubscriptions() {
        viewModel.getIsEmptyCart().observe(viewLifecycleOwner, Observer {
            if (it) {
                showEmptyStateLayout()
            } else {
                showCartLayout()
            }
        })

        viewModel.getMoviesLiveData().observe(viewLifecycleOwner, Observer {
            cartViewAdapter = CartViewAdapter(context, it, viewModel.cartDomain)
            setCartViewAdapter()
        })
    }

    private fun evaluateCartState() {
        viewModel.setCurrentCartItem()
    }

    private fun setElements(movies: ArrayList<Movie>, viewModelCartItem: CartViewViewModel) {
        viewModel = viewModelCartItem
        viewModel.setCurrentMovies(movies)
    }

    private fun setCartViewAdapter() {
        layoutCartViewBinding.rvProductList.adapter = cartViewAdapter
    }

    private fun showEmptyStateLayout() {
        layoutCartViewBinding.emptyStateContainer.visibility = View.VISIBLE
        layoutCartViewBinding.cartContainer.visibility = View.GONE
    }

    private fun showCartLayout() {
        layoutCartViewBinding.cartContainer.visibility = View.VISIBLE
        layoutCartViewBinding.emptyStateContainer.visibility = View.GONE
    }

    private fun setLayoutManager() {
        linearLayoutManager = LinearLayoutManager(context)
        layoutCartViewBinding.rvProductList.apply {
            layoutManager = linearLayoutManager
        }
    }

    fun setMovies(movies: ArrayList<Movie>) {
        viewModel.setCurrentMovies(movies)
    }
}
