package com.github.hosseinzafari.touristo.presentation.screens.location_description

import com.github.hosseinzafari.touristo.base.system.mvi.XAction
import com.github.hosseinzafari.touristo.base.system.mvi.XEffect
import com.github.hosseinzafari.touristo.base.system.mvi.XState
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.dto.LocationModel

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 15/06/2023 - 4:39 PM
 * @project Touristo
 */

data class LocationDescState(
    var data: LocationModel?,
    var liked: Boolean,
    var bookmarked: Boolean,
    override var status: XStatus,
    override val effects: LocationDescEffect?
) : XState<LocationDescEffect>

sealed class LocationDescEffect : XEffect {
    object NavigateToBack : LocationDescEffect()
    data class NavigateToComment(
        val descId: Int
    ) : LocationDescEffect()

}


sealed class LocationDescAction : XAction {
    data class GetLocationDescData(
        val id: Int
    ) : LocationDescAction()
    object ClickOnBackButton: LocationDescAction()
    object ClickOnLikeButton: LocationDescAction()
    object ClickOnBookmarkButton: LocationDescAction()
    object ClickOnCommentButton: LocationDescAction()
}