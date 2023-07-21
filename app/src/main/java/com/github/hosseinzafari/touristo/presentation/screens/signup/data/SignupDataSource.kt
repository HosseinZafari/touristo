package com.github.hosseinzafari.touristo.presentation.screens.signup.data

import com.github.hosseinzafari.touristo.core.Tables
import com.github.hosseinzafari.touristo.core.data.data_model.User
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.util.*
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 20/07/2023 - 2:52 PM
 * @project Touristo
 */

class SignupDataSource @Inject constructor(
    private val client: GoTrue,
    private val db: Postgrest,

    ) : SignupDomain {

    override suspend fun signup(email: String, password: String, name: String): Flow<User> = flow {
        client.signUpWith(Email) {
            this.email = email
            this.password = password
            data = buildJsonObject {
                put("name", name)
            }
        }

        val user = User(UUID.randomUUID().toString(), name, email, password, null)
        db[Tables.User.name].insert(value = user, upsert = true)
        emit(user)
    }
}