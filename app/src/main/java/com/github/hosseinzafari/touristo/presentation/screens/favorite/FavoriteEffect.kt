package com.github.hosseinzafari.touristo.presentation.screens.favorite

import com.github.hosseinzafari.touristo.base.system.mvi.XEffect

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 04/06/2023 - 3:22 PM
 * @project Touristo
 */

sealed class FavoriteEffect : XEffect {

    object NavigateToHome : FavoriteEffect()

}