package com.github.hosseinzafari.touristo.core.data.local

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 01/05/2023 - 11:15 PM
 * @project Touristo
 */

object DataStoreKeys {
    val idKey = stringPreferencesKey("id")
    val nameKey = stringPreferencesKey("name")
    val emailKey = stringPreferencesKey("email")
    val isLogin = booleanPreferencesKey("is_login")
    val loginAuthTimeKey = stringPreferencesKey("login_auth_time")
    val migratedFakeDataKey = booleanPreferencesKey("migrated_fake_data")
}