package com.github.hosseinzafari.touristo.core.data.local.transaction

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.github.hosseinzafari.touristo.core.data.local.DataStoreKeys
import com.github.hosseinzafari.touristo.core.data.local.domain.AppSettingDomain
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 03/05/2023 - 5:59 PM
 * @project Touristo
 */

class AppSettingImpl @Inject constructor(
    val dataStore: DataStore<Preferences>
) : AppSettingDomain {


    override suspend fun isMigratedFakeData() = dataStore.data.map {
        it[DataStoreKeys.migratedFakeDataKey] ?: false
    }.first()

    override suspend fun setMigratedFakeData(migrated: Boolean) {
        dataStore.edit {
            it[DataStoreKeys.migratedFakeDataKey] = migrated
        }
    }

    override suspend fun isUserLogined() = dataStore.data.map {
        it[DataStoreKeys.isLogin] ?: false
    }.first()
}