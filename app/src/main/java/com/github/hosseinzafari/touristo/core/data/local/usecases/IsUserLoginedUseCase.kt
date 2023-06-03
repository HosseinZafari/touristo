package com.github.hosseinzafari.touristo.core.data.local.usecases

import com.github.hosseinzafari.touristo.base.system.data_layer.XUseCase
import com.github.hosseinzafari.touristo.core.data.local.domain.AppSettingDomain
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 17/05/2023 - 4:28 PM
 * @project Touristo
 */

class IsUserLoginedUseCase @Inject constructor(
    override val domain: AppSettingDomain
) : XUseCase<AppSettingDomain>() {
     suspend operator fun invoke() = domain.isUserLogined()
}