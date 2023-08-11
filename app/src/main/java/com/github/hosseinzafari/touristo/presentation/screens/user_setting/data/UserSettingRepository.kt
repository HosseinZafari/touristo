package com.github.hosseinzafari.touristo.presentation.screens.user_setting.data

import com.github.hosseinzafari.touristo.core.data.data_model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 09/08/2023 - 11:41 AM
 * @project Touristo
 */

class UserSettingRepository @Inject constructor(
    val dataSource: UserSettingDataSource ,
): UserSettingDomain {

    override suspend fun getUser() = dataSource.getUser()
}