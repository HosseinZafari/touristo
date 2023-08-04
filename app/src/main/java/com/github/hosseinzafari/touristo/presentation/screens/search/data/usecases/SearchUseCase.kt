package com.github.hosseinzafari.touristo.presentation.screens.search.data.usecases

import com.github.hosseinzafari.touristo.base.system.data_layer.XUseCase
import com.github.hosseinzafari.touristo.presentation.screens.search.data.SearchDomain
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 01/08/2023 - 12:01 PM
 * @project Touristo
 */

class SearchUseCase @Inject constructor(override val domain: SearchDomain) :
    XUseCase<SearchDomain>() {

    suspend operator fun invoke(text: String) = domain.search(text)
}