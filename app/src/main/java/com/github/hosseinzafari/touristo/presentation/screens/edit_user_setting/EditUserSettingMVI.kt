package com.github.hosseinzafari.touristo.presentation.screens.edit_user_setting

import com.github.hosseinzafari.touristo.base.system.mvi.XAction
import com.github.hosseinzafari.touristo.base.system.mvi.XEffect
import com.github.hosseinzafari.touristo.base.system.mvi.XState
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.data_model.User

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 07/08/2023 - 2:38 PM
 * @project Touristo
 */

data class EditUserSettingState(
    val lastUser: User?,
    val firstName: String,
    val lastName: String,
    val imageUri: String?,
    val needUploadImage: Boolean = false,
    val desc: String?,
    val needToSave: Boolean,
    override var status: XStatus,
    override val effects: EditUserSettingEffect?
) : XState<EditUserSettingEffect>

sealed class EditUserSettingEffect : XEffect {
    object NavigateToBack : EditUserSettingEffect()
}

sealed class EditUserSettingAction : XAction {
    object  ClickOnBack : EditUserSettingAction()
    object  GetData : EditUserSettingAction()
    object  Submit : EditUserSettingAction()
    data class OnChangeFirstName(
        val text: String
    ) : EditUserSettingAction()

    data class OnChangeLastName(
        val text: String
    ) : EditUserSettingAction()

    data class OnChangeImageUri(
        val text: String
    ) : EditUserSettingAction()

    data class OnChangeDesc(
        val text: String
    ) : EditUserSettingAction()
}