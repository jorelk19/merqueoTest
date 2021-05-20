package com.merqueo.repository.local.mapper

import com.merqueo.businessmodels.business.Cart
import com.merqueo.businessmodels.business.Movie
import com.merqueo.repository.local.entities.CartDTO
import com.merqueo.repository.local.entities.MovieCartItemDTO

class LocalRepositoryCartMapper {
    companion object {
        fun mapCartDTO(cart: Cart): CartDTO {
            val cartDTO = CartDTO()
            cartDTO.id = cart.cartId
            cartDTO.movies.addAll(cart.movies.map {
                val movieCartItemDto = MovieCartItemDTO()
                movieCartItemDto.id = it!!.id
                movieCartItemDto.quantity = it.quantity
                movieCartItemDto
            })
            cartDTO.state = cart.state
            return cartDTO
        }

        fun mapCart(cartDTO: CartDTO): Cart {
            return Cart(
                cartId = cartDTO.id,
                movies = ArrayList<Movie>(cartDTO.movies.map {
                    Movie(
                        id = it.id,
                        quantity = it.quantity
                    )
                }),
                state = cartDTO.state
            )
        }
    }
}