package com.github.hosseinzafari.touristo.presentation.screens.favorite

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.dto.BookmarkData
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 04/06/2023 - 3:21 PM
 * @project Touristo
 */
@HiltViewModel
class BookmarkViewModel @Inject constructor(): XViewModel<BookmarkEffect , BookmarkAction , BookmarkState>() {
    override val processor = processor(
        initialState = BookmarkState(
            data = listOf() ,
            status = XStatus.Idle ,
            effects = null ,
        ) ,
        actionReducer = ::reducer
    )


    private fun reducer(oldState: BookmarkState, action: BookmarkAction) {
        when(action) {
            is BookmarkAction.GetData -> {
                processor.setState(oldState.copy(status = XStatus.Loading))

                viewModelScope.launch {
                    delay(2500L)
                    Log.i("Test" , "BookmarkData $BookmarkData")
                    /*processor.setState(oldState.copy(
                        data = BookmarkData.map {
                            LocationData.filter {location ->
                                it.locationID == location.id
                            }.first()
                        } ,
                        status = XStatus.Idle
                    ))*/
                }

            }

            is BookmarkAction.ClickOnBackButton -> {
                processor.setState(oldState.copy(effects = BookmarkEffect.NavigateToBack))
            }

            is BookmarkAction.ClickOnLocationCard -> {
                processor.setState(oldState.copy(effects = BookmarkEffect.NavigateToLocationCard(action.id)))
            }
        }
    }

}