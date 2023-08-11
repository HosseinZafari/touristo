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
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds


@Module()
@InstallIn(SingletonComponent::class)
object SupabaseModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient = createSupabaseClient(BuildConfig.URL, BuildConfig.API_KEY) {
        install(Postgrest)
        install(Storage) {
            transferTimeout = 240.seconds // equal 4 minute
        }
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
    fun provideStorage(
        client: SupabaseClient
    ) : Storage = client.storage


    @Provides
    @Singleton
    fun providePostgres(
        client: SupabaseClient
    ) : Postgrest = client.postgrest

}