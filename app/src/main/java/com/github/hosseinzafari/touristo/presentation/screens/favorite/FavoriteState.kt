package com.github.hosseinzafari.touristo.presentation.screens.favorite

import com.github.hosseinzafari.touristo.base.system.mvi.XState
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 04/06/2023 - 3:21 PM
 * @project Touristo
 */

data class FavoriteState(
    override var status: XStatus,
    override val effects: FavoriteEffect?
) : XState<FavoriteEffect>