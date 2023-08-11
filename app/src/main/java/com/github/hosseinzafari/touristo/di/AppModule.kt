package com.github.hosseinzafari.touristo.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.github.hosseinzafari.touristo.core.data.local.datasource.AppSettingImpl
import com.github.hosseinzafari.touristo.core.data.local.domain.AppSettingDomain
import com.github.hosseinzafari.touristo.presentation.screens.add_location.data.AddLocationDataSource
import com.github.hosseinzafari.touristo.presentation.screens.add_location.data.AddLocationDomain
import com.github.hosseinzafari.touristo.presentation.screens.add_location.data.AddLocationRepository
import com.github.hosseinzafari.touristo.presentation.screens.bookmark.data.BookmarkDataSource
import com.github.hosseinzafari.touristo.presentation.screens.bookmark.data.BookmarkDomain
import com.github.hosseinzafari.touristo.presentation.screens.bookmark.data.BookmarkRepository
import com.github.hosseinzafari.touristo.presentation.screens.comment.data.CommentDataSource
import com.github.hosseinzafari.touristo.presentation.screens.comment.data.CommentDomain
import com.github.hosseinzafari.touristo.presentation.screens.comment.data.CommentRepository
import com.github.hosseinzafari.touristo.presentation.screens.edit_user_setting.data.EditUserDataSource
import com.github.hosseinzafari.touristo.presentation.screens.edit_user_setting.data.EditUserDomain
import com.github.hosseinzafari.touristo.presentation.screens.edit_user_setting.data.EditUserRepository
import com.github.hosseinzafari.touristo.presentation.screens.home.data.HomeDataSource
import com.github.hosseinzafari.touristo.presentation.screens.home.data.HomeDomain
import com.github.hosseinzafari.touristo.presentation.screens.home.data.HomeRepository
import com.github.hosseinzafari.touristo.presentation.screens.location_description.data.LocationDescDataSource
import com.github.hosseinzafari.touristo.presentation.screens.location_description.data.LocationDescDomain
import com.github.hosseinzafari.touristo.presentation.screens.location_description.data.LocationDescRepository
import com.github.hosseinzafari.touristo.presentation.screens.login.data.LoginDomain
import com.github.hosseinzafari.touristo.presentation.screens.login.data.LoginLocalDataSource
import com.github.hosseinzafari.touristo.presentation.screens.login.data.LoginRepo
import com.github.hosseinzafari.touristo.presentation.screens.search.data.SearchDataSource
import com.github.hosseinzafari.touristo.presentation.screens.search.data.SearchDomain
import com.github.hosseinzafari.touristo.presentation.screens.search.data.SearchRepository
import com.github.hosseinzafari.touristo.presentation.screens.signup.data.SignupDataSource
import com.github.hosseinzafari.touristo.presentation.screens.signup.data.SignupDomain
import com.github.hosseinzafari.touristo.presentation.screens.signup.data.SignupRepo
import com.github.hosseinzafari.touristo.presentation.screens.user_setting.data.UserSettingDataSource
import com.github.hosseinzafari.touristo.presentation.screens.user_setting.data.UserSettingDomain
import com.github.hosseinzafari.touristo.presentation.screens.user_setting.data.UserSettingRepository
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
    fun provideUserSettingDomain(
        dataSource: UserSettingDataSource
    ) : UserSettingDomain = UserSettingRepository(dataSource)

    @Provides
    @Singleton
    fun provideEditUserDomain(
        dataSource: EditUserDataSource
    ) : EditUserDomain = EditUserRepository(dataSource)

    @Provides
    @Singleton
    fun provideAddLocationDomain(
        dataSource: AddLocationDataSource
    ) : AddLocationDomain = AddLocationRepository(dataSource)

    @Provides
    @Singleton
    fun provideCommentDescDomain(
        dataSource: CommentDataSource
    ) : CommentDomain = CommentRepository(dataSource)

    @Provides
    @Singleton
    fun provideLocationDescDomain(
        dataSource: LocationDescDataSource
    ) : LocationDescDomain = LocationDescRepository(dataSource)

    @Provides
    @Singleton
    fun provideBookmarkDomain(
        dataSource: BookmarkDataSource
    ) : BookmarkDomain = BookmarkRepository(dataSource)

    @Provides
    @Singleton
    fun provideSearchDomain(
        dataSource: SearchDataSource
    ) : SearchDomain = SearchRepository(dataSource)

    @Provides
    @Singleton
    fun provideHomeDomain(
        homeDataSource: HomeDataSource
    ) : HomeDomain = HomeRepository(homeDataSource)

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