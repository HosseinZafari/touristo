package com.github.hosseinzafari.touristo.core.data.local.usecases

import com.github.hosseinzafari.touristo.base.system.data_layer.XUseCase
import com.github.hosseinzafari.touristo.core.data.local.domain.AppSettingDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 03/05/2023 - 6:08 PM
 * @project Touristo
 */


class IsMigratedFakeDataUseCase @Inject constructor(
    override val domain: AppSettingDomain
) : XUseCase<AppSettingDomain>() {

    suspend operator fun invoke() = domain.isMigratedFakeData()

}