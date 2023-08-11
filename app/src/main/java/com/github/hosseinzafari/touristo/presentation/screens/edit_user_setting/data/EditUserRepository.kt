package com.github.hosseinzafari.touristo.presentation.screens.edit_user_setting.data

import com.github.hosseinzafari.touristo.core.data.data_model.User
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 08/08/2023 - 2:08 PM
 * @project Touristo
 */

 
class EditUserRepository @Inject constructor(
    val dataSource: EditUserDataSource,
): EditUserDomain  {

    override suspend fun updateUser(newUser: User) = dataSource.updateUser(newUser)
    override suspend fun uploadUserImage(file: File) = dataSource.uploadUserImage(file)

    override suspend fun getUser() = dataSource.getUser()

}