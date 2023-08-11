package com.github.hosseinzafari.touristo.presentation.screens.user_setting.data.usecases

import com.github.hosseinzafari.touristo.base.system.data_layer.XUseCase
import com.github.hosseinzafari.touristo.presentation.screens.user_setting.data.UserSettingDomain
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 09/08/2023 - 11:59 AM
 * @project Touristo
 */

class GetUserUseCase @Inject constructor(
    override val domain: UserSettingDomain
) : XUseCase<UserSettingDomain>() {

    suspend operator fun invoke() = domain.getUser()
}