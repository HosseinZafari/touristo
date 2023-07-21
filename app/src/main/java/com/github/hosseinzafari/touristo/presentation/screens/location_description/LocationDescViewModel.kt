package com.github.hosseinzafari.touristo.presentation.screens.location_description

import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.data_model.*
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 04/06/2023 - 4:21 PM
 * @project Touristo
 */
@HiltViewModel
class LocationDescViewModel @Inject constructor() : XViewModel<LocationDescEffect,LocationDescAction,LocationDescState>() {
    override val processor = processor(
        initialState = LocationDescState(
            data = null ,
            liked = false ,
            bookmarked = false ,
            status = XStatus.Idle ,
            effects = null ,
        ) ,
        actionReducer = ::reducer
    )


    private fun reducer(oldState: LocationDescState , action: LocationDescAction) {
        when (action) {
            is LocationDescAction.GetLocationDescData -> {
                processor.setState(oldState.copy(
                    status = XStatus.Loading ,
                ))

                viewModelScope.launch {
                    delay(2000L)
                    /*processor.setState(oldState.copy(
                        data = LocationData.filter {
                            it.id == action.id
                        }.firstOrNull() ,
                        liked = LikeData.filter {
                            it.locationID == action.id && it.userID == 1
                        }.firstOrNull().let {
                            if (it == null) false else true
                        } ,
                        bookmarked = BookmarkData.filter {
                            it.locationID == action.id && it.userID == 1
                        }.firstOrNull().let {
                            if (it == null) false else true
                        } ,
                        status = XStatus.Idle ,
                    ))*/
                }
            }

            is LocationDescAction.ClickOnBookmarkButton -> {
                BookmarkData.add(BookmarkModel(1 , 1 , oldState.data!!.id))

                processor.setState(oldState.copy(
                    bookmarked = BookmarkData.filter {
                        it.locationID == oldState.data!!.id && it.userID == 1
                    }.firstOrNull().let {
                        if (it == null) false else true
                    }
                ))
            }

            is LocationDescAction.ClickOnBackButton -> {
                processor.setState(oldState.copy(
                    effects = LocationDescEffect.NavigateToBack
                ))
            }

            is LocationDescAction.ClickOnCommentButton -> {
                processor.setState(oldState.copy(
                    effects = LocationDescEffect.NavigateToComment(oldState.data!!.id)
                ))
            }

            is LocationDescAction.ClickOnLikeButton ->  {
                LikeData.add(LikeModel(1 , 1 , oldState.data!!.id))

                processor.setState(oldState.copy(
                    liked = LikeData.filter {
                        it.locationID == oldState.data!!.id && it.userID == 1
                    }.firstOrNull().let {
                        if (it == null) false else true
                    }
                ))

            }
        }
    }

}