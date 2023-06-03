package com.github.hosseinzafari.touristo.presentation.screens.signup.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.github.hosseinzafari.touristo.core.data.data_model.User
import com.github.hosseinzafari.touristo.core.data.local.DataStoreKeys
import com.github.hosseinzafari.touristo.core.data.local.realm_schema.UserRealm
import com.github.hosseinzafari.touristo.core.data.local.realm_schema.toUserRealm
import io.realm.Realm
import kotlinx.coroutines.flow.flow
import org.bson.types.ObjectId
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 10/05/2023 - 12:10 AM
 * @project Touristo
 */

class SignupLocalDataSource @Inject constructor(
    val dataStore: DataStore<Preferences>
) : SignupDomain {

    override suspend fun alreadyUserExists(email: String) = flow {
        val realm = Realm.getDefaultInstance()
        var isExists = false

        realm.executeTransaction {
            isExists = it.where(UserRealm::class.java)
                .equalTo("email", email)
                .findFirst() != null
        }

        emit(isExists)
    }

    override suspend fun signup(user: User) = flow {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            user.id = ObjectId().toHexString()
            it.insertOrUpdate(user.toUserRealm())
        }

        emit(user)
    }

    override suspend fun saveUserInfo(
        id: String,
        name: String,
        email: String,
        isLogin: Boolean,
        loginAuthTime: String
    ) {
        dataStore.edit {
            it[DataStoreKeys.idKey] = id
            it[DataStoreKeys.nameKey] = name
            it[DataStoreKeys.emailKey] = email
            it[DataStoreKeys.isLogin] = isLogin
            it[DataStoreKeys.loginAuthTimeKey] = loginAuthTime
        }
    }
}