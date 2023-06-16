package com.github.hosseinzafari.touristo.presentation.screens.comment

import com.github.hosseinzafari.touristo.base.system.mvi.XAction
import com.github.hosseinzafari.touristo.base.system.mvi.XEffect
import com.github.hosseinzafari.touristo.base.system.mvi.XState
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 06/06/2023 - 7:07 PM
 * @project Touristo
 */

data class CommentState(
    val data: List<String> ,
    val comment: String ,
    override var status: XStatus,
    override val effects: CommentEffect?
) : XState<CommentEffect>


sealed class CommentEffect : XEffect {
    object NavigateToHome: CommentEffect()
}

sealed class CommentAction : XAction {
    object Submit: CommentAction()
    data class  OnChangeComment(
        val text: String
    ) : CommentAction()
}



