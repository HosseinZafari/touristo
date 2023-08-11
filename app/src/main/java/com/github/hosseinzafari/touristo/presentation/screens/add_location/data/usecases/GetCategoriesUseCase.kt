package com.github.hosseinzafari.touristo.presentation.screens.add_location.data.usecases

import com.github.hosseinzafari.touristo.base.system.data_layer.XUseCase
import com.github.hosseinzafari.touristo.core.data.data_model.Category
import com.github.hosseinzafari.touristo.presentation.screens.add_location.data.AddLocationDomain
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 06/08/2023 - 1:10 PM
 * @project Touristo
 */

class GetCategoriesUseCase @Inject constructor(
    override val domain: AddLocationDomain
) : XUseCase<AddLocationDomain>() {

    suspend operator fun invoke() = domain.getCategories()

}