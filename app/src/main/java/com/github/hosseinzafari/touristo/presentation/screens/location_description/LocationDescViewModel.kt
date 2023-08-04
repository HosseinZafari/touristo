package com.github.hosseinzafari.touristo.presentation.screens.location_description

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.data_model.Bookmark
import com.github.hosseinzafari.touristo.core.data.dto.LikeModel
import com.github.hosseinzafari.touristo.presentation.screens.location_description.data.usecases.*
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 04/06/2023 - 4:21 PM
 * @project Touristo
 */
@HiltViewModel
class LocationDescViewModel @Inject constructor(
    val getLocation: GetLocationUseCase,
    val isExistsBookmark: IsExistsBookmarkUseCase,
    val isExistsLike: IsExistsLikeUseCase,
    val addLike: AddLikeUseCase,
    val addBookmark: AddBookmarkUseCase,
    val removeBookmark: RemoveBookmarkUseCase ,
    val removeLike: RemoveLikeUseCase ,
) : XViewModel<LocationDescEffect, LocationDescAction, LocationDescState>() {
    override val processor = processor(
        initialState = LocationDescState(
            data = null,
            liked = null,
            bookmarked = null,
            status = XStatus.Idle,
            effects = null,
        ),
        actionReducer = ::reducer
    )


    private fun reducer(oldState: LocationDescState, action: LocationDescAction) {
        when (action) {
            is LocationDescAction.GetLocationDescData -> {
                processor.setState(
                    oldState.copy(
                        status = XStatus.Loading,
                    )
                )

                viewModelScope.launch(Dispatchers.IO) {
                    try {

                        val locationInfo = async {
                            getLocation(action.id).first()
                        }
                        val isLiked = async {
                            isExistsLike(action.id).first()
                        }
                        val isBookmarked = async {
                            isExistsBookmark(action.id).first()
                        }



                        processor.setState(oldState.copy(
                            data =  locationInfo.await(),
                            liked = isLiked.await(),
                            bookmarked = isBookmarked.await(),
                            status = XStatus.Idle,
                        ))
                    } catch (err: Exception) {
                        Log.i("Test", "Error in getting data err: " + err)
                        processor.setState(
                            oldState.copy(
                                status = XStatus.Error("خطا در برقراری ارتباط")
                            )
                        )
                    }
                }
            }

            is LocationDescAction.ClickOnBookmarkButton -> {
                val currentBookmark = oldState.bookmarked
                processor.setState(oldState.copy(
                    bookmarked = null ,
                ))

                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val locationID = oldState.data!!.id
                        if (currentBookmark != null && currentBookmark) { // unbookmark
                            removeBookmark(locationID)
                        } else { // bookrmark
                            addBookmark(locationID)
                        }

                        processor.setState(oldState.copy(
                            bookmarked = isExistsBookmark(locationID).first() ,
                        ))

                    } catch (err: Exception) {
                        Log.i("Test" , "Error in liking location ERR: " + err)
                        processor.setState(oldState.copy(
                            bookmarked = false ,
                            status = XStatus.Error("خطا در برقراری ارتباط")
                        ))
                    }
                }
            }

            is LocationDescAction.ClickOnBackButton -> {
                processor.setState(
                    oldState.copy(
                        effects = LocationDescEffect.NavigateToBack
                    )
                )
            }

            is LocationDescAction.ClickOnCommentButton -> {
                processor.setState(
                    oldState.copy(
                        effects = LocationDescEffect.NavigateToComment(oldState.data!!.id)
                    )
                )
            }

            is LocationDescAction.ClickOnLikeButton -> {
                val currentLike = oldState.liked
                processor.setState(oldState.copy(
                    liked = null ,
                ))

                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val locationID = oldState.data!!.id
                        if(currentLike != null && currentLike) { // unline
                            removeLike(locationID)
                        } else { // like
                            addLike(locationID)
                        }

                        processor.setState(oldState.copy(
                            liked = isExistsLike(locationID).first() ,
                        ))

                    } catch (err: Exception) {
                        Log.i("Test" , "Error in liking location ERR: " + err)
                        processor.setState(oldState.copy(
                            liked = false ,
                            status = XStatus.Error("خطا در برقراری ارتباط")
                        ))
                    }
                }
            }
        }
    }

}