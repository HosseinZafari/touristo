package com.github.hosseinzafari.touristo.presentation.screens.login.data.domain

import com.github.hosseinzafari.touristo.base.system.data_layer.XDomain
import com.github.hosseinzafari.touristo.core.data.data_model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 01/05/2023 - 9:43 PM
 * @project Touristo
 */

interface LoginDomain : XDomain{
    suspend fun findUser(email: String, password: String): Flow<User?>
    suspend fun saveUserInfo(id: String, name: String, email: String, isLogin: Boolean , loginAuthTime: String)
}