package com.merqueo.edson.ui.views.fragments.adapters

import android.content.Context
import com.merqueo.businessmodels.business.Movie
import com.merqueo.domain.cart.CartDomain
import com.merqueo.edson.R
import com.merqueo.edson.databinding.LayoutCartItemBinding
import com.merqueo.edson.ui.viewModels.CartItemViewModel
import com.merqueo.utils.GenericAdapter

class CartViewAdapter(context: Context?, arrayList: ArrayList<Movie>, private val cartDomain: CartDomain) : GenericAdapter<Movie, LayoutCartItemBinding>(context, arrayList) {

    /**
     * Method to retrieve the layout for the item in rmnecycler view
     * */
    override fun getLayoutResId(): Int {
        return R.layout.layout_cart_item
    }

    /**
     * Method that allow load information into view model
     * */
    override fun onBindData(model: Movie, position: Int, dataBinding: LayoutCartItemBinding) {
        val cartItemViewModel = CartItemViewModel(cartDomain)
        dataBinding.viewModel = cartItemViewModel
        cartItemViewModel.setCartItemData(model)
    }
}