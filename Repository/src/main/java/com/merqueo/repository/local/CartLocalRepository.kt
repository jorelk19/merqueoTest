package com.merqueo.repository.local

import com.merqueo.businessmodels.business.Cart
import com.merqueo.repository.local.entities.CartDTO
import com.merqueo.repository.local.mapper.LocalRepositoryCartMapper
import io.realm.Realm

class CartLocalRepository : ILocalRepositoryManager<Cart, CartDTO> {
    private var realm: Realm = Realm.getDefaultInstance()
    override fun update(cart: Cart) {
        val cartDTO = LocalRepositoryCartMapper.mapCartDTO(cart)
        val storedCart = realm.where(CartDTO::class.java).equalTo("_id", cartDTO.id).findFirst()
        storedCart?.let {
            cartDTO.id = it.id
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(cartDTO)
            realm.commitTransaction()
        }
    }

    override fun create(cart: Cart): Int {
        val index = realm.where(CartDTO::class.java).max("_id")
        val cartDto = LocalRepositoryCartMapper.mapCartDTO(cart)
        index?.let { num ->
            cartDto.id = num.toInt() + 1
        } ?: run { cartDto.id = 1 }

        realm.executeTransaction { currentRealm ->
            currentRealm.copyToRealm(cartDto)
        }

        return cartDto.id
    }

    override fun delete(cart: Cart) {
    }

    override fun read(cartDto: CartDTO, fieldName: String, fieldValue: Any, castType : String): Cart {
        val currentCartDto = when(castType){
            "INTEGER" ->  realm.where(CartDTO::class.java).equalTo(fieldName, fieldValue as Int).findFirst()
            "STRING" -> realm.where(CartDTO::class.java).equalTo(fieldName, fieldValue.toString()).findFirst()
            else -> realm.where(CartDTO::class.java).equalTo(fieldName, fieldValue.toString()).findFirst()
        }

        var cart = Cart()

        currentCartDto?.let { cartDtoResult ->
            cart = LocalRepositoryCartMapper.mapCart(cartDtoResult)
        } ?: run {
            cart = Cart(cartId = -1)
        }
        return cart
    }

    override fun removeAll(cart: CartDTO) {
    }

    override fun getAll(element: CartDTO): ArrayList<Cart> {
        return arrayListOf()
    }
}