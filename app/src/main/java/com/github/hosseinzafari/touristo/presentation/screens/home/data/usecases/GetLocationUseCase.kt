package com.github.hosseinzafari.touristo.presentation.screens.home.data.usecases

import com.github.hosseinzafari.touristo.base.system.data_layer.XUseCase
import com.github.hosseinzafari.touristo.presentation.screens.home.data.HomeDomain
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 30/07/2023 - 2:29 PM
 * @project Touristo
 */
 
 class GetLocationUseCase @Inject constructor(
    override val domain: HomeDomain,
) : XUseCase<HomeDomain>() {

    suspend operator fun invoke(categoryId: Int) = domain.getLocationByCategoryID(categoryId)
}