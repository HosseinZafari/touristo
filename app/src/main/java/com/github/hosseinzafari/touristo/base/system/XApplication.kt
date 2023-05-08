package com.github.hosseinzafari.touristo.base.system

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import timber.log.Timber

/*
* XApp is base application in our framework and
* useful for set automatically setting to Application context and ONCE manage them
* */
abstract class XApp : Application() {
    // language app setting
    abstract var lang : XLang
    // db is a database general config for the app (Room , Realm , etc ...)
    abstract var db: XDB
    // datastore local cache info
    abstract val datastore: DataStore<Preferences>
    // appScope is our coroutine scope in application state
    val appScope = MainScope()

    override fun onCreate() {
        super.onCreate()
        Timber.i("Application Running [${packageName}] ")
        initDependencies()
    }

    /*
    * initialise all of our component and libs (third party lib) in onCreate application state
    * */
    abstract fun initDependencies()

    /*
    * Clean or cancel all of our process
    * */
    override fun onLowMemory() {
        super.onLowMemory()
        Timber.i("Application onLowMemory [${packageName}] - Cancel appScope Process ")
        appScope.cancel()
    }

}