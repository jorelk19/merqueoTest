package com.merqueo.edson.ui.viewModels

import androidx.lifecycle.MutableLiveData
import com.merqueo.edson.ui.viewModels.base.BaseViewModel
import com.merqueo.utils.Navigation

/**
 * Class used to manage the view model for the header view
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class HeaderViewModel : BaseViewModel() {

    var headerTitle = MutableLiveData<String>()
    var isBackVisibility = MutableLiveData<Boolean>()

    /**
     * Function to execute back in header button
     * */
    fun onBackHeader(){
        Navigation.getInstance.onBack()
    }

    /**
     * Method to set the values in header
     * */
    fun setHeaderValues(headerTitle: String, isBackVisibility: Boolean) {
        this.headerTitle.value = headerTitle
        this.isBackVisibility.value = isBackVisibility
    }
}