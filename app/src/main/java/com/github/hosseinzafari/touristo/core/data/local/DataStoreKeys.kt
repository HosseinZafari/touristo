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
    val id = stringPreferencesKey("id")
    val name = stringPreferencesKey("name")
    val email = stringPreferencesKey("email")
    val isLoggedIn = booleanPreferencesKey("is_logged_in")
}