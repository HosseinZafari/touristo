package com.github.hosseinzafari.touristo.presentation.screens.login.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.github.hosseinzafari.touristo.core.data.data_model.User
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 01/05/2023 - 9:52 PM
 * @project Touristo
 */

class LoginLocalDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val auth: GoTrue,
) : LoginDomain {

    override suspend fun findUser(email: String, password: String): Flow<User?> = flow {
        auth.loginWith(Email) {
            this.email = email
            this.password = password
        }


        val user = auth.currentUserOrNull()
        if (user != null) {
            emit(User(user.id , user.userMetadata?.get("name").toString(), "" , "" , user.email , null , null))
            return@flow
        }

        emit(null)
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