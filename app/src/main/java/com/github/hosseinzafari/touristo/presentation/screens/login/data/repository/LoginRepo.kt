package com.github.hosseinzafari.touristo.presentation.screens.login.data.repository

import com.github.hosseinzafari.touristo.core.data.data_model.User
import com.github.hosseinzafari.touristo.presentation.screens.login.data.datasource.LoginLocalDataSource
import com.github.hosseinzafari.touristo.presentation.screens.login.data.domain.LoginDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 01/05/2023 - 9:48 PM
 * @project Touristo
 */

class LoginRepo @Inject constructor(
    val local: LoginLocalDataSource
) : LoginDomain {

    override suspend fun findUser(email: String, password: String): Flow<User?> {
        return local.findUser(email , password)
    }


    override suspend fun saveUserInfo(
        id: String,
        name: String,
        email: String,
        isLogin: Boolean ,
        loginAuthTime: String
    ) = local.saveUserInfo(id , name , email , isLogin ,loginAuthTime)
}