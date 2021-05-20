package com.merqueo.edson.ui.views.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleObserver
import com.merqueo.utils.Navigation

/**
 * Abstract class to manage the frame layout to create custom controls and manage the life cycle observer
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
abstract class BaseControl : FrameLayout, LifecycleObserver {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttribute: Int) : super(context, attributeSet, defStyleAttribute)

    /**
     * Method to attach the window
     * */
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Navigation.getInstance.getCurrentActivity().lifecycle.addObserver(this)
    }
}