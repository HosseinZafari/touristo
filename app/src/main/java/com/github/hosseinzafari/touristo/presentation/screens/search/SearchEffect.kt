package com.github.hosseinzafari.touristo.presentation.screens.search

import com.github.hosseinzafari.touristo.base.system.mvi.XEffect

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 02/06/2023 - 2:49 PM
 * @project Touristo
 */

sealed class SearchEffect : XEffect {
    object NavigateToHome  : SearchEffect()
    data class NavigateToLocationCardItem(
        val locationID: String
    ): SearchEffect()

}