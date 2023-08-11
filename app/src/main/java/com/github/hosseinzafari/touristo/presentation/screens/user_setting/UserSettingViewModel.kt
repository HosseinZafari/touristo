package com.github.hosseinzafari.touristo.presentation.screens.user_setting

import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import com.github.hosseinzafari.touristo.presentation.screens.user_setting.data.usecases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 07/08/2023 - 10:43 AM
 * @project Touristo
 */


@HiltViewModel
class UserSettingViewModel @Inject constructor(
    val getData: GetUserUseCase ,
) : XViewModel<UserSettingEffect, UserSettingAction, UserSettingState>() {

    override val processor = processor(
        initialState = UserSettingState(
            user = null,
            status = XStatus.Idle,
            effects = null,
        ),
        actionReducer = ::reducer
    )


    private fun reducer(oldState: UserSettingState, action: UserSettingAction) {
        when (action) {
            is UserSettingAction.GetData -> {
                processor.setState(oldState.copy(status = XStatus.Loading))
                viewModelScope.launch(Dispatchers.IO) {
                    val user = getData().first()
                    processor.setState(oldState.copy(
                        user = user ,
                        status = XStatus.Idle
                    ))
                }
            }

            is UserSettingAction.ClickOnBackButton -> {
                processor.setState(oldState.copy(effects = UserSettingEffect.NavigateToBack))
            }
            is UserSettingAction.ClickOnBookmarkItem -> {
                processor.setState(oldState.copy(effects = UserSettingEffect.NavigateToBookmark))
            }
            is UserSettingAction.ClickOnEditProfile -> {
                processor.setState(oldState.copy(effects = UserSettingEffect.NavigateToEditUserSetting))
            }

            is UserSettingAction.ClickOnLogoutItem -> {
                processor.setState(oldState.copy(effects = UserSettingEffect.NavigateToEditUserSetting))
            }
        }
    }

}