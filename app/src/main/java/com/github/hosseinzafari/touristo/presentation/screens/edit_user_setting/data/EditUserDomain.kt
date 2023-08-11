package com.github.hosseinzafari.touristo.presentation.screens.edit_user_setting.data

import com.github.hosseinzafari.touristo.base.system.data_layer.XDomain
import com.github.hosseinzafari.touristo.core.data.data_model.User
import kotlinx.coroutines.flow.Flow
import java.io.File

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 08/08/2023 - 2:08 PM
 * @project Touristo
 */
 
interface EditUserDomain : XDomain {

    suspend fun updateUser(newUser: User)
    suspend fun uploadUserImage(file: File): String

    suspend fun getUser(): Flow<User>

}