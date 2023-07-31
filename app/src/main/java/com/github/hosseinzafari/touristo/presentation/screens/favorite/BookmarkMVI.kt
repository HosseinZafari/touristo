package com.github.hosseinzafari.touristo.presentation.screens.favorite

import com.github.hosseinzafari.touristo.base.system.mvi.XAction
import com.github.hosseinzafari.touristo.base.system.mvi.XEffect
import com.github.hosseinzafari.touristo.base.system.mvi.XState
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.dto.LocationModel

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 16/06/2023 - 10:53 AM
 * @project Touristo
 */

sealed class BookmarkAction : XAction {
    object GetData: BookmarkAction()
    object ClickOnBackButton: BookmarkAction()

    data class ClickOnLocationCard(
        val id: Int
    ): BookmarkAction()
}


sealed class BookmarkEffect : XEffect {

    object NavigateToBack : BookmarkEffect()
    data class NavigateToLocationCard(
        val id: Int
    ) : BookmarkEffect()

}

data class BookmarkState(
    var data:  List<LocationModel>,
    override var status: XStatus,
    override val effects: BookmarkEffect?
) : XState<BookmarkEffect>