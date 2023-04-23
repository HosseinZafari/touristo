package com.github.hosseinzafari.touristo

import com.github.hosseinzafari.touristo.base.system.XApp
import com.github.hosseinzafari.touristo.base.system.XLang

class TouristApplication() : XApp() {
    override var lang: XLang = FaLang

    override fun onCreate() {
        super.onCreate()

        initLang()
    }

    private fun initLang() {
        lang = FaLang
        L =  FaLang
    }
}