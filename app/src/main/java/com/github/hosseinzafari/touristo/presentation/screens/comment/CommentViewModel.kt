package com.github.hosseinzafari.touristo.presentation.screens.comment

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.presentation.screens.comment.data.usecases.AddCommentUseCase
import com.github.hosseinzafari.touristo.presentation.screens.comment.data.usecases.GetCommentsUseCase
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 06/06/2023 - 7:12 PM
 * @project Touristo
 */

@HiltViewModel
class CommentViewModel @Inject constructor(
    val getComments: GetCommentsUseCase ,
    val addComment: AddCommentUseCase ,
) :
    XViewModel<CommentEffect, CommentAction, CommentState>() {
    override val processor = processor(
        initialState = CommentState(
            data = listOf(),
            comment = "",
            status = XStatus.Idle,
            effects = null
        ),
        actionReducer = ::reducer
    )


    private fun reducer(oldState: CommentState, action: CommentAction) {
        when (action) {
            is CommentAction.OnChangeComment ->
                processor.setState(oldState.copy(comment = action.text))

            is CommentAction.Submit -> {
                if (oldState.comment.isBlank()) {
                    return
                }

                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        addComment(action.id , oldState.comment)

                        processor.setState(oldState.copy(comment = ""))
                        processor.sendAction(CommentAction.GetData(action.id))
                    } catch (err: Exception) {
                        Log.i("Test" , "Error in adding a comment ERR: " + err)
                        processor.setState(oldState.copy(
                            status = XStatus.Error("خطا در برقراری ارتباط")
                        ))
                    }

                }
            }

            is CommentAction.GetData -> {
                processor.setState(oldState.copy(status = XStatus.Loading))
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val comments = getComments(action.id).first()
                        processor.setState(oldState.copy(
                            data = comments ,
                            status = XStatus.Idle
                        ))
                    } catch (err: Exception) {
                        Log.i("Test" , "Error in getting data ERR: " + err )
                        processor.setState(oldState.copy(
                            status = XStatus.Error("مشکل در برقراری ارتباط")
                        ))
                    }

                }

            }
        }
    }
}