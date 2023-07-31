package com.github.hosseinzafari.touristo.presentation.screens.search

import com.github.hosseinzafari.touristo.base.system.mvi.XAction
import com.github.hosseinzafari.touristo.base.system.mvi.XEffect
import com.github.hosseinzafari.touristo.base.system.mvi.XState
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.dto.LocationModel
import com.github.hosseinzafari.touristo.core.data.dto.ProvinceModel

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 14/06/2023 - 3:31 PM
 * @project Touristo
 */

sealed class SearchAction : XAction {
    object Submit: SearchAction()
    data class OnSearchChanged(
        val text: String
    ) : SearchAction()

    data class ClickOnLocationCard(
        val locationID : Int
    ) : SearchAction()

    data class GetSelectedProvince(
        val provinceID: Int
    )  : SearchAction()
}


data class SearchState(
    val text: String,
    val data: List<LocationModel>,
    val selectedProvince: ProvinceModel?,
    override var status: XStatus,
    override val effects: SearchEffect?
) : XState<SearchEffect>

sealed class SearchEffect : XEffect {
    object NavigateToHome  : SearchEffect()

    data class NavigateToLocationCardItem(
        val locationID: Int
    ): SearchEffect()

}