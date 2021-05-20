package com.merqueo.edson.ui.views.activities.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentActivity
import com.merqueo.utils.Navigation

/**
 * Class used to manage the activities in the application
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
open class BaseFragmentActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Navigation.getInstance.setCurrentActivity(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Navigation.getInstance.setCurrentActivity(this)
    }

    override fun onResume() {
        super.onResume()
        Navigation.getInstance.setCurrentActivity(this)
    }

    override fun onBackPressed() {
        Navigation.getInstance.onBack()
    }
}