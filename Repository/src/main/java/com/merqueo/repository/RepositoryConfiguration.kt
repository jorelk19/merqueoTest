package com.merqueo.repository

import android.content.Context
import io.realm.Realm

/**
 * Class used to initialize realm in the current context
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
class RepositoryConfiguration {
    companion object{
        fun startRealm(context: Context){
            Realm.init(context)
        }
    }
}