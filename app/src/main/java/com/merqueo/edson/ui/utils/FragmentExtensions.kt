package com.merqueo.edson.ui.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Extension functions for Fragment.
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 */
fun Fragment.getViewModelFactory(): com.merqueo.edson.ui.viewModels.base.ViewModelFactory {
    return com.merqueo.edson.ui.viewModels.base.ViewModelFactory()
}

fun FragmentActivity.getViewModelFactory(): com.merqueo.edson.ui.viewModels.base.ViewModelFactory {
    return com.merqueo.edson.ui.viewModels.base.ViewModelFactory()
}