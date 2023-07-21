package com.github.hosseinzafari.touristo.core.data.local.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.github.hosseinzafari.touristo.core.data.local.DataStoreKeys
import com.github.hosseinzafari.touristo.core.data.local.domain.AppSettingDomain
import io.github.jan.supabase.gotrue.GoTrue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 03/05/2023 - 5:59 PM
 * @project Touristo
 */

class AppSettingImpl @Inject constructor(
    val dataStore: DataStore<Preferences>,
    val auth: GoTrue
) : AppSettingDomain {


    override suspend fun isUserLoggedIn() = auth.loadFromStorage(true)

//        return dataStore.data.map {
//            it[DataStoreKeys.isLoggedIn] ?: false
//        }.first()

    override suspend fun saveUser(id: String , email: String, name: String) {
        dataStore.edit {
            it[DataStoreKeys.id] = id
            it[DataStoreKeys.isLoggedIn] = true
            it[DataStoreKeys.name] = name
            it[DataStoreKeys.email] = email
        }
    }
}