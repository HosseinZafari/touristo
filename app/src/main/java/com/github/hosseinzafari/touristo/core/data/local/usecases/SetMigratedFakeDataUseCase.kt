package com.github.hosseinzafari.touristo.core.data.local.usecases

import com.github.hosseinzafari.touristo.base.system.data_layer.XUseCase
import com.github.hosseinzafari.touristo.core.data.local.domain.AppSettingDomain
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 04/05/2023 - 1:56 PM
 * @project Touristo
 */

class SetMigratedFakeDataUseCase @Inject constructor(
    override val domain: AppSettingDomain
) : XUseCase<AppSettingDomain>() {

    operator suspend fun invoke(migrated: Boolean) = domain.setMigratedFakeData(migrated)
}