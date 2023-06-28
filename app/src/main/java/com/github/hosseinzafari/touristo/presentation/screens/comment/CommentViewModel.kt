package com.github.hosseinzafari.touristo.presentation.screens.comment

import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.data_model.CommentModel
import com.github.hosseinzafari.touristo.core.data.data_model.LocationData
import com.github.hosseinzafari.touristo.core.data.local.fake_data.fakeUsers
import com.github.hosseinzafari.touristo.core.data.local.fake_data.toUser
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Integer.parseInt
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 06/06/2023 - 7:12 PM
 * @project Touristo
 */

@HiltViewModel
class CommentViewModel @Inject constructor() :
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
                LocationData.filter {
                    action.id == it.id
                }.first()
                    .comments
                    .add(
                        CommentModel(
                            (Math.random() * 1000).toInt(),
                            fakeUsers.get(0).toUser(),
                            oldState.comment
                        )
                    )

                processor.setState(oldState.copy(comment = ""))
                processor.sendAction(CommentAction.GetData(action.id))
            }

            is CommentAction.GetData -> {
                processor.setState(oldState.copy(status = XStatus.Loading))
                viewModelScope.launch {
                    delay(1000L)
                    processor.setState(oldState.copy(
                        data = LocationData.filter {
                            it.id == action.id
                        }.flatMap { it.comments },
                        status = XStatus.Idle
                    )
                    )
                }

            }
        }
    }
}