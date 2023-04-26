package com.github.hosseinzafari.touristo

import com.github.hosseinzafari.touristo.base.system.XApp
import com.github.hosseinzafari.touristo.base.system.XDB
import com.github.hosseinzafari.touristo.base.system.XLang
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm


@HiltAndroidApp
class TouristApplication() : XApp() {
    override var lang: XLang = FaLang
    override var db = object : XDB {
        override val dbName = "realm_db_${packageName}"
        override val version = 1L
    }

    override fun onCreate() {
        super.onCreate()
    }


    override fun initDependencies() {
        initLibs()
        initLang()
    }

    private fun initLibs() {
        // initialise realm database once
        Realm.init(this)
    }

    private fun initLang() {
        lang = FaLang
        L = FaLang
    }
}

