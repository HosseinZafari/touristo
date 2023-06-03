package com.github.hosseinzafari.touristo.presentation.screens.signup.data

import com.github.hosseinzafari.touristo.base.system.data_layer.XDomain
import com.github.hosseinzafari.touristo.core.data.data_model.User
import kotlinx.coroutines.flow.Flow

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 09/05/2023 - 11:59 PM
 * @project Touristo
 */
 
interface SignupDomain : XDomain {
    suspend fun alreadyUserExists(email: String): Flow<Boolean>
    suspend fun signup(user: User): Flow<User>
    suspend fun saveUserInfo(id: String, name: String, email: String, isLogin: Boolean , loginAuthTime: String)

}