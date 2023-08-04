package com.github.hosseinzafari.touristo.presentation.screens.bookmark

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.presentation.screens.bookmark.data.usecases.GetBookmarksUseCase
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 04/06/2023 - 3:21 PM
 * @project Touristo
 */
@HiltViewModel
class BookmarkViewModel @Inject constructor(
    val getBookmarks: GetBookmarksUseCase
) : XViewModel<BookmarkEffect, BookmarkAction, BookmarkState>() {
    override val processor = processor(
        initialState = BookmarkState(
            data = listOf(),
            status = XStatus.Idle,
            effects = null,
        ),
        actionReducer = ::reducer
    )


    private fun reducer(oldState: BookmarkState, action: BookmarkAction) {
        when (action) {
            is BookmarkAction.GetData -> {
                processor.setState(oldState.copy(status = XStatus.Loading))
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val bookmarks = getBookmarks().first().map { it.location }

                        processor.setState(
                            oldState.copy(
                                data = bookmarks,
                                status = XStatus.Idle
                            )
                        )
                    } catch (err: Exception) {
                        Log.i("Test" , "Error in getBookmarks " + err )
                        processor.setState(
                            oldState.copy(
                                status = XStatus.Error("مشکل در برقراری ارتباط")
                            )
                        )

                    }
                }

            }

            is BookmarkAction.ClickOnBackButton -> {
                processor.setState(oldState.copy(effects = BookmarkEffect.NavigateToBack))
            }

            is BookmarkAction.ClickOnLocationCard -> {
                processor.setState(
                    oldState.copy(
                        effects = BookmarkEffect.NavigateToLocationCard(
                            action.id
                        )
                    )
                )
            }
        }
    }

}