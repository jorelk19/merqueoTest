package com.merqueo.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

interface INavigation {
    fun setCurrentActivity(fragmentActivity: FragmentActivity)
    fun getCurrentActivity(): FragmentActivity
    fun <T> goTo(classTo: Class<T>, bundle: Bundle? = null, flags: IntArray = intArrayOf())
    fun attachFragment(
        fragmentTo: Fragment,
        container: Int,
        bundle: Bundle? = null,
        addNewTransaction: Boolean = false,
        addToBackStack: Boolean = true
    )
    fun showLoader()
    fun hideLoader()
    fun onBack()
    fun getString(resourceId: Int): String
    fun setInternetConnection(hasInternet: Boolean)
    fun hasInternet() : Boolean
}