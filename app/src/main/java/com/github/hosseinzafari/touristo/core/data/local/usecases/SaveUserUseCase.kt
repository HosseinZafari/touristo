package com.github.hosseinzafari.touristo.core.data.local.usecases

import com.github.hosseinzafari.touristo.base.system.data_layer.XUseCase
import com.github.hosseinzafari.touristo.core.data.local.domain.AppSettingDomain
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 21/07/2023 - 1:35 PM
 * @project Touristo
 */

class SaveUserUseCase @Inject constructor(override val domain: AppSettingDomain) :
    XUseCase<AppSettingDomain>() {

    operator suspend fun invoke(id: String , email: String, name: String) = domain.saveUser(id , email, name)

}