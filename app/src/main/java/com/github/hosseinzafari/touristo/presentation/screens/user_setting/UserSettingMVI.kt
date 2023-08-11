package com.github.hosseinzafari.touristo.presentation.screens.user_setting

import com.github.hosseinzafari.touristo.base.system.mvi.XAction
import com.github.hosseinzafari.touristo.base.system.mvi.XEffect
import com.github.hosseinzafari.touristo.base.system.mvi.XState
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.data_model.User

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 07/08/2023 - 10:44 AM
 * @project Touristo
 */


data class UserSettingState(
    val user: User?,
    override var status: XStatus,
    override val effects: UserSettingEffect?
) : XState<UserSettingEffect>


sealed class UserSettingEffect : XEffect {
    object NavigateToEditUserSetting: UserSettingEffect()
    object NavigateToSignup: UserSettingEffect()
    object NavigateToBack: UserSettingEffect()
    object NavigateToBookmark: UserSettingEffect()
}

sealed class UserSettingAction : XAction {

    object GetData : UserSettingAction()
    object ClickOnBackButton : UserSettingAction()
    object ClickOnBookmarkItem : UserSettingAction()
    object ClickOnEditProfile : UserSettingAction()
    object ClickOnLogoutItem : UserSettingAction()

}