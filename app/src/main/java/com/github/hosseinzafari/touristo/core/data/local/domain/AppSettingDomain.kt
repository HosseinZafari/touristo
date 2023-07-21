package com.github.hosseinzafari.touristo.core.data.local.domain

import com.github.hosseinzafari.touristo.base.system.data_layer.XDomain
import kotlinx.coroutines.flow.Flow

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 03/05/2023 - 5:57 PM
 * @project Touristo
 */
 
interface AppSettingDomain : XDomain {
    suspend fun isUserLoggedIn(): Boolean
    suspend fun saveUser(id: String , email: String , name: String)
}