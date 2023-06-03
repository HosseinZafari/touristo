package com.github.hosseinzafari.touristo.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.github.hosseinzafari.touristo.TouristApplication
import com.github.hosseinzafari.touristo.core.data.local.domain.AppFakeDataDomain
import com.github.hosseinzafari.touristo.core.data.local.domain.AppSettingDomain
import com.github.hosseinzafari.touristo.core.data.local.transaction.AppFakeDataImpl
import com.github.hosseinzafari.touristo.core.data.local.transaction.AppSettingImpl
import com.github.hosseinzafari.touristo.presentation.screens.login.data.LoginDomain
import com.github.hosseinzafari.touristo.presentation.screens.login.data.LoginLocalDataSource
import com.github.hosseinzafari.touristo.presentation.screens.login.data.LoginRepo
import com.github.hosseinzafari.touristo.presentation.screens.signup.data.SignupDomain
import com.github.hosseinzafari.touristo.presentation.screens.signup.data.SignupLocalDataSource
import com.github.hosseinzafari.touristo.presentation.screens.signup.data.SignupRepo
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
    fun provideLoginDomain(
        loginLocalDataSource: LoginLocalDataSource
    ) : LoginDomain {
        return LoginRepo(loginLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideSignupDomain(
        signupLocalDataSource: SignupLocalDataSource ,
    ) : SignupDomain {
        return SignupRepo(signupLocalDataSource)
    }

}