package com.merqueo.edson.ui.views.activities.splashScreen

import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.merqueo.edson.R
import com.merqueo.edson.databinding.ActivitySplashScreenBinding
import com.merqueo.edson.ui.views.activities.MainActivity
import com.merqueo.edson.ui.views.activities.base.BaseFragmentActivity
import com.merqueo.utils.Navigation

/**
 * Activity use to show splash screen when the application start
 * @author Edson Joel Nieto Ardila *
 * @since 1.0.0
 * */
class SplashScreenActivity : BaseFragmentActivity() {

    private lateinit var activitySplashScreenBinding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashScreenBinding = DataBindingUtil.setContentView(this@SplashScreenActivity, R.layout.activity_splash_screen)

        Handler().postDelayed(Runnable {
            Navigation.getInstance.goTo(MainActivity::class.java)
            this.finish()
        }, 2000)
    }
}