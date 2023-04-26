package com.github.hosseinzafari.touristo.di

import android.app.Application
import android.content.Context
import com.github.hosseinzafari.touristo.App
import com.github.hosseinzafari.touristo.TouristApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ThirdPartyLibModule {

    @Provides
    @Singleton
    fun provideRealmConfiguration(
        @ApplicationContext context: Context
    ) = RealmConfiguration.Builder().schemaVersion(App(context)!!.db.version)
        .name(App(context)!!.db.dbName).build()


    @Singleton
    @Provides
    fun provideRealm(
        realmConfiguration: RealmConfiguration
    ) = Realm.getInstance(realmConfiguration)

}