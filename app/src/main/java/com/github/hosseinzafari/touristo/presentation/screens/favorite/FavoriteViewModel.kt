package com.github.hosseinzafari.touristo.presentation.screens.favorite

import com.github.hosseinzafari.touristo.base.system.mvi.XProcessor
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 04/06/2023 - 3:21 PM
 * @project Touristo
 */
@HiltViewModel
class FavoriteViewModel @Inject constructor(): XViewModel<FavoriteEffect , FavoriteAction , FavoriteState>() {
    override val processor = processor(
        initialState = FavoriteState(
            status = XStatus.Idle ,
            effects = null ,
        ) ,

        actionReducer = ::reducer
    )


    private fun reducer(oldState: FavoriteState, action: FavoriteAction) {
        when(action) {
            is FavoriteAction.ClickOnBackButton -> {
            }

            is FavoriteAction.ClickOnLocationCard -> {
            }
        }
    }

}