package com.github.hosseinzafari.touristo.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.github.hosseinzafari.touristo.App
import com.github.hosseinzafari.touristo.core.TouristApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


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

}