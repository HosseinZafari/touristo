package com.github.hosseinzafari.touristo.base.system

import android.app.Application
import timber.log.Timber

/*
* XApp is base application in our framework and
* useful for set automatically setting to Application context and ONCE manage them
* */
abstract class XApp : Application() {
    abstract var lang : XLang

    override fun onCreate() {
        super.onCreate()

        Timber.i("Application Running [${packageName}] ")
    }
}