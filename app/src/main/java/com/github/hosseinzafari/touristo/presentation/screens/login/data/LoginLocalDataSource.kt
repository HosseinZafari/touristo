package com.github.hosseinzafari.touristo.presentation.screens.login.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.github.hosseinzafari.touristo.core.data.local.DataStoreKeys
import com.github.hosseinzafari.touristo.core.data.data_model.User
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 01/05/2023 - 9:52 PM
 * @project Touristo
 */

class LoginLocalDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : LoginDomain {

    override suspend fun findUser(email: String, password: String): Flow<User?> = flow {
        /*val realm = Realm.getDefaultInstance()
        var user: User? = null
        realm.executeTransaction {
            user = realm.where(UserRealm::class.java)
                .equalTo("email" , email)
                .and()
                .equalTo("password" , password)
                .findFirst()?.toUser()
        }

        emit(user)

        realm.close()*/
    }

    override suspend fun saveUserInfo(
        id: String,
        name: String,
        email: String,
        isLogin: Boolean ,
        loginAuthTime: String
    ) {

    }
}