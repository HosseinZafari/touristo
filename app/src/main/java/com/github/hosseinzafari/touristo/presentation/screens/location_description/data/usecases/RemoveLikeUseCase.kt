package com.github.hosseinzafari.touristo.presentation.screens.location_description.data.usecases

import com.github.hosseinzafari.touristo.base.system.data_layer.XUseCase
import com.github.hosseinzafari.touristo.presentation.screens.location_description.data.LocationDescDomain
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 03/08/2023 - 4:50 PM
 * @project Touristo
 */

class RemoveLikeUseCase @Inject constructor(
    override val domain: LocationDescDomain
) : XUseCase<LocationDescDomain>() {

    suspend operator fun invoke(locationID: Int) = domain.removeLike(locationID)

}