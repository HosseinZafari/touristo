package com.github.hosseinzafari.touristo.presentation.screens.home.data.usecases

import com.github.hosseinzafari.touristo.base.system.data_layer.XUseCase
import com.github.hosseinzafari.touristo.presentation.screens.home.data.HomeDomain
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 31/07/2023 - 1:40 PM
 * @project Touristo
 */

class GetCategoriesUseCase @Inject constructor(
    override val domain: HomeDomain
) : XUseCase<HomeDomain>(){

    suspend operator fun invoke() = domain.getCategories()

}