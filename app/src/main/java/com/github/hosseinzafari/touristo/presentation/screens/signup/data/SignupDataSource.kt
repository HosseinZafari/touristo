package com.github.hosseinzafari.touristo.presentation.screens.signup.data

import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
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

    override suspend fun signup(email: String, password: String ) {
        client.signUpWith(Email) {
            this.email = email
            this.password = password
        }
    }
}