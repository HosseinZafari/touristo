package com.github.hosseinzafari.touristo.presentation.screens.comment

import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 06/06/2023 - 7:12 PM
 * @project Touristo
 */

@HiltViewModel
class CommentViewModel @Inject constructor(): XViewModel<CommentEffect , CommentAction , CommentState>() {
    override val processor = processor(
        initialState = CommentState(
            data = listOf() ,
            comment = "" ,
            status = XStatus.Idle ,
            effects = null
        ) ,
        actionReducer = ::reducer
    )


    private fun reducer(oldState: CommentState , action: CommentAction) {
        when(action) {
            is CommentAction.OnChangeComment ->
                processor.setState(oldState.copy(comment = action.text))

            is CommentAction.Submit -> {

            }
        }
    }
}