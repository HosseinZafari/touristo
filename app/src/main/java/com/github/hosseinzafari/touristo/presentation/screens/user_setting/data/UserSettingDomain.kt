package com.github.hosseinzafari.touristo.presentation.screens.user_setting.data

import com.github.hosseinzafari.touristo.base.system.data_layer.XDomain
import com.github.hosseinzafari.touristo.core.data.data_model.User
import kotlinx.coroutines.flow.Flow

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 09/08/2023 - 11:40 AM
 * @project Touristo
 */
 
interface UserSettingDomain : XDomain {

    suspend fun getUser(): Flow<User>


}