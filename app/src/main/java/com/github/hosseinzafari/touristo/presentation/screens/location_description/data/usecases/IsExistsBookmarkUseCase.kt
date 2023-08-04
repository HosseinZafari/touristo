package com.github.hosseinzafari.touristo.presentation.screens.location_description.data.usecases

import com.github.hosseinzafari.touristo.base.system.data_layer.XUseCase
import com.github.hosseinzafari.touristo.core.data.data_model.Bookmark
import com.github.hosseinzafari.touristo.presentation.screens.location_description.data.LocationDescDomain
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 02/08/2023 - 4:59 PM
 * @project Touristo
 */

class IsExistsBookmarkUseCase @Inject constructor(
    override val domain: LocationDescDomain ,
): XUseCase<LocationDescDomain>() {

    suspend operator fun invoke(locationID: Int) = domain.bookmarkIsExists(locationID)

}