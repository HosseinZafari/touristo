package com.github.hosseinzafari.touristo.presentation.screens.comment

import com.github.hosseinzafari.touristo.base.system.mvi.XAction
import com.github.hosseinzafari.touristo.base.system.mvi.XEffect
import com.github.hosseinzafari.touristo.base.system.mvi.XState
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.data_model.Comment
import com.github.hosseinzafari.touristo.core.data.dto.CommentModel

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 06/06/2023 - 7:07 PM
 * @project Touristo
 */

data class CommentState(
    val data: List<Comment>,
    val comment: String,
    override var status: XStatus,
    override val effects: CommentEffect?
) : XState<CommentEffect>


sealed class CommentEffect : XEffect {
    object NavigateToBack: CommentEffect()

}

sealed class CommentAction : XAction {
    data class  Submit(
        val id : Int
    ): CommentAction()
    data class GetData(
        val id: Int
    ): CommentAction()

    data class  OnChangeComment(
        val text: String
    ) : CommentAction()
}



