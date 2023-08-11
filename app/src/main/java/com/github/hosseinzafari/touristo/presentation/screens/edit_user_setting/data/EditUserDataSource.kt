package com.github.hosseinzafari.touristo.presentation.screens.edit_user_setting.data

import android.util.Log
import com.github.hosseinzafari.touristo.core.Bucket
import com.github.hosseinzafari.touristo.core.Tables
import com.github.hosseinzafari.touristo.core.data.data_model.User
import com.github.hosseinzafari.touristo.core.data.dto.UserModel
import com.github.hosseinzafari.touristo.core.data.dto.toUser
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 08/08/2023 - 2:08 PM
 * @project Touristo
 */

class EditUserDataSource @Inject constructor(
    val db: Postgrest,
    val auth: GoTrue,
    val storage: Storage,
) : EditUserDomain {

    override suspend fun uploadUserImage(file: File) : String {
        val user = auth.currentUserOrNull()
        if (user == null) {
            throw Exception("User Not Found")
        }

        val fileName = user.id + "." + file.name
        val result = storage[Bucket.Profile.text].upload(fileName , file.readBytes() , upsert = true )
        Log.i("Test" , "result uploaded file key: $result filename $fileName")

        return storage[Bucket.Profile.text].publicUrl(fileName)
    }

    override suspend fun updateUser(newUser: User) {
        val user = auth.currentUserOrNull()
        if (user == null) {
            throw Exception("Current User Not Found")
        }

        db[Tables.User.text].update({
            UserModel::desc setTo newUser.desc
            UserModel::name setTo newUser.name
            UserModel::family setTo newUser.family
            UserModel::imageUri setTo newUser.profileUrl
        }) {
            eq("id" , user.id)
        }
    }

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