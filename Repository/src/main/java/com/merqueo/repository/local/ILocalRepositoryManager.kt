package com.merqueo.repository.local

import com.merqueo.businessmodels.business.Movie
import com.merqueo.repository.local.entities.MovieDTO

/**
 * Interface that manage the methods that can be used in local repository
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
interface ILocalRepositoryManager<T, D>{

    fun update(element : T)
    fun create(element : T) : Int
    fun delete(element : T)
    fun read(element : D, fieldName : String, fieldValue : Any, castType : String) : T
    fun removeAll(element : D)
    fun getAll(element : D) : ArrayList<T>
}