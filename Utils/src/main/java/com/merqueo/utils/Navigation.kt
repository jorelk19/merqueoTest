package com.merqueo.utils

import android.animation.AnimatorInflater
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Class used to manage the loaders, inflate activities and fragments
 * */
class Navigation : INavigation {

    private lateinit var currentActivity: FragmentActivity
    private var loaderDialogView: Dialog? = null
    private var internetState: Boolean = false

    companion object {
        private var instance: Navigation? = null
        val getInstance: Navigation
            get() {
                if (instance == null)
                    instance = Navigation()
                return instance!!
            }
    }


    /**
     * Method to set the current activity
     * */
    override fun setCurrentActivity(fragmentActivity: FragmentActivity) {
        currentActivity = fragmentActivity
    }

    /**
     * Method to get teh current activity
     * */
    override fun getCurrentActivity(): FragmentActivity {
        return currentActivity
    }

    /**
     * Method to launch an activity
     * */
    override fun <T> goTo(
        classTo: Class<T>,
        bundle: Bundle?,
        flags: IntArray
    ) {
        val intent = Intent(currentActivity, classTo)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        bundle?.let { intent.putExtras(it) }
        if (flags.isNotEmpty())
            for (flag in flags) {
                intent.addFlags(flag)
            }
        currentActivity.startActivity(intent)
    }

    /**
     * Method to inflate a fragment an activity container
     * */
    override fun attachFragment(
        fragmentTo: Fragment,
        container: Int,
        bundle: Bundle?,
        addNewTransaction: Boolean,
        addToBackStack: Boolean
    ) {
        bundle?.let { fragmentTo.arguments = it }

        val currentFragmentTransaction = currentActivity.supportFragmentManager
            .beginTransaction()
        // Check if backStack is required
        if (addToBackStack) {
            currentFragmentTransaction.addToBackStack(fragmentTo.tag)
        }
        // Check if add / replace case
        if (addNewTransaction)
            currentFragmentTransaction.add(container, fragmentTo)
        else
            currentFragmentTransaction.replace(container, fragmentTo)
        // Verify stateSaved for supportFragmentManager
        if (currentActivity.supportFragmentManager.isStateSaved) {
            currentFragmentTransaction.commitAllowingStateLoss()
        } else {
            currentFragmentTransaction.commit()
        }
    }

    /**
     * Method to show a simple loader
     * */
    override fun showLoader() {
        if (loaderDialogView == null) {
            loaderDialogView = Dialog(currentActivity)
            loaderDialogView!!.setContentView(R.layout.layout_loader)
            AnimatorInflater.loadAnimator(currentActivity, R.animator.flipping).apply {
                setTarget(loaderDialogView?.findViewById(R.id.imgSpinnerIcon))
                duration = 500
            }.start()
            loaderDialogView?.setCancelable(false)
            loaderDialogView?.show()
            loaderDialogView?.window?.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            loaderDialogView?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    /**
     * Method to hide simple loader
     * */
    override fun hideLoader() {
        loaderDialogView?.let { dialog ->
            if (dialog.isShowing) {
                dialog.dismiss()
            }
            loaderDialogView = null
        }
    }

    /**
     * Method to back between fragments
     * */
    override fun onBack() {
        if (currentActivity.supportFragmentManager.backStackEntryCount > 1) {
            currentActivity.supportFragmentManager.popBackStack()
        }
    }

    /**
     * Method to get strings from resources
     * */
    override fun getString(resourceId: Int): String {
        return currentActivity.getString(resourceId)
    }

    /**
     * Method that allow set internet connection
     * */
    override fun setInternetConnection(hasInternet: Boolean) {
        internetState = hasInternet
    }

    /**
     * Method to return the connection state
     * */
    override fun hasInternet() = internetState

}