package com.github.hosseinzafari.touristo.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.github.hosseinzafari.touristo.TouristApplication
import com.github.hosseinzafari.touristo.core.data.local.domain.AppFakeDataDomain
import com.github.hosseinzafari.touristo.core.data.local.domain.AppSettingDomain
import com.github.hosseinzafari.touristo.core.data.local.transaction.AppFakeDataImpl
import com.github.hosseinzafari.touristo.core.data.local.transaction.AppSettingImpl
import com.github.hosseinzafari.touristo.presentation.screens.login.data.datasource.LoginLocalDataSource
import com.github.hosseinzafari.touristo.presentation.screens.login.data.domain.LoginDomain
import com.github.hosseinzafari.touristo.presentation.screens.login.data.repository.LoginRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppFakeDataDomain(
    ) : AppFakeDataDomain {
        return AppFakeDataImpl()
    }

    @Provides
    @Singleton
    fun provideAppSettingDomain(
        dataStore: DataStore<Preferences>,
    ) : AppSettingDomain {
        return AppSettingImpl(dataStore)
    }

    @Provides
    @Singleton
    fun provideLoginLocalDataSource(
        dataStore: DataStore<Preferences>,
    ) = LoginLocalDataSource(dataStore)


    @Provides
    @Singleton
    fun provideLoginDomain(
        loginLocalDataSource: LoginLocalDataSource
    ) : LoginDomain {
        return LoginRepo(loginLocalDataSource)
    }

}