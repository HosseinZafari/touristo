package com.github.hosseinzafari.touristo.presentation.screens.search

import com.github.hosseinzafari.touristo.base.system.mvi.XAction

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 02/06/2023 - 2:50 PM
 * @project Touristo
 */

sealed class SearchAction : XAction {
    object Submit: SearchAction()
    data class OnSearchChanged(
        val text: String
    ) : SearchAction()

    data class ClickOnLocationCard(
        val locationID : String
    ) : SearchAction()
}