package com.merqueo.edson.ui.viewModels

import com.merqueo.edson.ui.viewModels.mocks.movieDetail1
import org.junit.Before
import org.junit.Test

class HeaderViewModelTest {
    private lateinit var headerViewModel: HeaderViewModel

    @Before
    fun setup() {
        headerViewModel = HeaderViewModel()
    }


    @Test
    fun validateSetViewModelProperties(){
        headerViewModel.setHeaderValues(movieDetail1.title!!, true)

        assert(headerViewModel.headerModel.headerTitle == "movie1")
    }
}