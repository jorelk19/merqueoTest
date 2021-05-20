package com.merqueo.businessmodels.errors

import retrofit2.HttpException

/**
 * Interface used to manage the exceptions events
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
interface IErrorManager {
    fun onServiceErrorHttpException(error : String?, httpException: HttpException)
    fun onSocketTimeoutException(error : String?)
    fun onIOException(error : String?)
    fun onHideLoader()
    fun onShowLoader()
}