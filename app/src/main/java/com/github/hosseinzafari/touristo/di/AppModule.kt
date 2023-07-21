package com.github.hosseinzafari.touristo.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.github.hosseinzafari.touristo.core.data.local.datasource.AppSettingImpl
import com.github.hosseinzafari.touristo.core.data.local.domain.AppSettingDomain
import com.github.hosseinzafari.touristo.presentation.screens.login.data.LoginDomain
import com.github.hosseinzafari.touristo.presentation.screens.login.data.LoginLocalDataSource
import com.github.hosseinzafari.touristo.presentation.screens.login.data.LoginRepo
import com.github.hosseinzafari.touristo.presentation.screens.signup.data.SignupDataSource
import com.github.hosseinzafari.touristo.presentation.screens.signup.data.SignupDomain
import com.github.hosseinzafari.touristo.presentation.screens.signup.data.SignupRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.gotrue.GoTrue
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideSignupDomain(
        signupDataSource: SignupDataSource
    ) : SignupDomain = SignupRepo(signupDataSource)

    @Provides
    @Singleton
    fun provideLoginDomain(
        loginLocalDataSource: LoginLocalDataSource
    ) : LoginDomain =  LoginRepo(loginLocalDataSource)

    @Provides
    @Singleton
    fun provideAppSettingDomain(
        datastore: DataStore<Preferences> ,
        auth: GoTrue ,
    ) : AppSettingDomain = AppSettingImpl(datastore , auth)



}