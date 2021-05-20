package com.merqueo.businessmodels.business

data class Cart (val cartId: Int = 0, val movies: ArrayList<Movie> = arrayListOf(), val state : String = "")