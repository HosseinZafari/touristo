package com.github.hosseinzafari.touristo.presentation.screens.user_setting.data

import com.github.hosseinzafari.touristo.core.Tables
import com.github.hosseinzafari.touristo.core.data.dto.UserModel
import com.github.hosseinzafari.touristo.core.data.dto.toUser
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 09/08/2023 - 11:41 AM
 * @project Touristo
 */

class  UserSettingDataSource @Inject constructor(
    val db: Postgrest,
    val auth: GoTrue,
) : UserSettingDomain {
    override suspend fun getUser() = flow {
        val user = auth.currentUserOrNull()
        if (user == null) {
            throw Exception("Current User Not Found")
        }

        emit(
            db[Tables.User.text].select() {
                eq("id", user.id)
            }.decodeSingle<UserModel>().toUser()
        )
    }

}