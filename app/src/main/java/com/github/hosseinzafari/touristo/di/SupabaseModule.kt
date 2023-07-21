package com.github.hosseinzafari.touristo.di

import com.github.hosseinzafari.touristo.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Singleton


@Module()
@InstallIn(SingletonComponent::class)
object SupabaseModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient = createSupabaseClient(BuildConfig.URL, BuildConfig.API_KEY) {
        install(Postgrest)
        install(GoTrue) {
            host = "touristo.auth"
            scheme = "app"
        }
    }

    @Provides
    @Singleton
    fun provideGoTrue(
        client: SupabaseClient
    ) : GoTrue = client.gotrue


    @Provides
    @Singleton
    fun providePostgres(
        client: SupabaseClient
    ) : Postgrest = client.postgrest

}