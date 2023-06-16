package com.github.hosseinzafari.touristo.presentation.screens.favorite

import com.github.hosseinzafari.touristo.base.system.mvi.XAction

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 04/06/2023 - 3:23 PM
 * @project Touristo
 */

sealed class FavoriteAction : XAction {
    object ClickOnBackButton: FavoriteAction()
    object ClickOnLocationCard: FavoriteAction()
}