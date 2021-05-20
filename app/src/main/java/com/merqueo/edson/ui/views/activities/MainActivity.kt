package com.merqueo.edson.ui.views.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.merqueo.edson.R
import com.merqueo.edson.databinding.ActivityMainBinding
import com.merqueo.edson.ui.utils.getViewModelFactory
import com.merqueo.edson.ui.viewModels.CartViewViewModel
import com.merqueo.edson.ui.viewModels.MainViewModel
import com.merqueo.edson.ui.views.activities.base.BaseFragmentActivity

class MainActivity : BaseFragmentActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel> { getViewModelFactory() }
    private val viewModelCartItem by viewModels<CartViewViewModel> { getViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        binding.viewModel = viewModel
        viewModel.loadCurrentCart(viewModelCartItem)
        initControls()
    }

    private fun initControls() {
        viewModel.attachMovieFragment()
    }
}