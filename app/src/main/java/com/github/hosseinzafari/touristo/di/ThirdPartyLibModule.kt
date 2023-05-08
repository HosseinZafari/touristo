package com.github.hosseinzafari.touristo.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.github.hosseinzafari.touristo.App
import com.github.hosseinzafari.touristo.TouristApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.properties.ReadOnlyProperty


@Module
@InstallIn(SingletonComponent::class)
object ThirdPartyLibModule {


    @Provides
    @Singleton
    fun provideTouristoApplication(
        @ApplicationContext context: Context
    ) : TouristApplication = App(context)!!


    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ) : DataStore<Preferences> = App(context)!!.datastore




    @Singleton
    @Provides
    fun provideRealm(
        @ApplicationContext context: Context ,
    ) :  RealmConfiguration  {
        Realm.init(context)
        return RealmConfiguration.Builder()
            .schemaVersion(App(context)!!.db.version)
            .name(App(context)!!.db.dbName)
            .build()
    }

}