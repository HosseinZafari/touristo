package com.github.hosseinzafari.touristo

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.github.hosseinzafari.touristo.base.system.*
import com.github.hosseinzafari.touristo.core.data.local.fake_data.fakeUsers
import com.github.hosseinzafari.touristo.core.data.local.usecases.*
import com.github.hosseinzafari.touristo.core.lang.FaLang
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltAndroidApp
class TouristApplication : XApp() {
    @Inject
    lateinit var logic: TouristoAppLogic

    override var lang: XLang = FaLang
    override var db = object : XDB {
        override val dbName = "realm_db_1"
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
        migrateFakeData()
    }

    private fun migrateFakeData() {
        appScope.launch(Dispatchers.IO) {
            logic.migrateFakeData()
        }

    }

    private fun initLibs() {
        Realm.init(this)
    }

    private fun initLang() {
        lang = FaLang
        L = FaLang
    }
}

