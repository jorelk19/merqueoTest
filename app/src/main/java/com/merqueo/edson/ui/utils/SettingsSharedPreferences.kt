package com.merqueo.edson.ui.utils

import android.content.Context
import android.content.SharedPreferences
import com.merqueo.utils.app.App

const val PREFERENCES_FILE_KEY = "com.mercadolibre.preferences"
const val CURRENT_SITE = "CURRENT_SITE"

/**
 * Class use to store setting shared preferences
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * **/
class SettingsSharedPreferences(appContext: Context) {

    private val sharedPreferences: SharedPreferences = appContext.getSharedPreferences(
        PREFERENCES_FILE_KEY, Context.MODE_PRIVATE
    )

    private fun int(key: String) = sharedPreferences.getInt(key, 0)
    private fun boolean(key: String) = sharedPreferences.getBoolean(key, false)
    private fun string(key: String) = sharedPreferences.getString(key, "") ?: ""
    private fun setInt(key: String, value: Int) = this.sharedPreferences.edit().putInt(key, value).commit()
    private fun setString(key: String, value: String) = this.sharedPreferences.edit().putString(key, value).commit()
    private fun setBoolean(key: String, value: Boolean) = this.sharedPreferences.edit().putBoolean(key, value).commit()

    fun setSite(site: String) {
        setString(CURRENT_SITE, site)
    }

    fun getCurrentSite(): String {
        return string(CURRENT_SITE)
    }
}

val settingsSharedPreferences by lazy { SettingsSharedPreferences(App.getAppContext()) }
