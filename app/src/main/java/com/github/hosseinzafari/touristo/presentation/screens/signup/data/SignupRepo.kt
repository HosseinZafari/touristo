package com.github.hosseinzafari.touristo.presentation.screens.signup.data

import com.github.hosseinzafari.touristo.core.data.data_model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 10/05/2023 - 12:11 AM
 * @project Touristo
 */

class SignupRepo @Inject constructor(
    val local: SignupLocalDataSource
) : SignupDomain {

    override suspend fun alreadyUserExists(email: String) = local.alreadyUserExists(email)
    override suspend fun signup(user: User) = local.signup(user)
    override suspend fun saveUserInfo(
        id: String,
        name: String,
        email: String,
        isLogin: Boolean,
        loginAuthTime: String
    ) = local.saveUserInfo(id , name , email , isLogin , loginAuthTime)
}