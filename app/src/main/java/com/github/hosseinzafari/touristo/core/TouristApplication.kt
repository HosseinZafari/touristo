package com.github.hosseinzafari.touristo.core

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.github.hosseinzafari.touristo.L
import com.github.hosseinzafari.touristo.base.system.*
import com.github.hosseinzafari.touristo.lang.FaLang
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class TouristApplication : XApp() {

    override var lang: XLang = FaLang
    override var db = object : XDB {
        override val dbName = "db_1"
        override val version = 1L
    }

    override val datastore: DataStore<Preferences> by preferencesDataStore(name = "user_setting")

    companion object {
        // for access out of any class or functions like globals need it this context , so
        // you can not using Hilt in globals , well use this context
        lateinit var currentApp: TouristApplication
    }

    override fun onCreate() {
        super.onCreate()
        currentApp = this
    }


    override fun initDependencies() {
        initLibs()
        initLang()
    }


    private fun initLibs() {
    }

    private fun initLang() {
        lang = FaLang
        L = FaLang
    }
}

