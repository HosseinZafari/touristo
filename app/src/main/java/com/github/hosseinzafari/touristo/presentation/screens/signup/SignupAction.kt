package com.github.hosseinzafari.touristo.presentation.screens.signup

import com.github.hosseinzafari.touristo.base.system.mvi.XAction

sealed class SignupAction : XAction {
    data class OnChangeName(val newText: String) : SignupAction()
    data class OnChangeEmail(val newText: String) : SignupAction()
    data class OnChangePassword(val newText: String) : SignupAction()
    data class OnChangeRePassword(val newText: String) : SignupAction()

    object LoginOnClick : SignupAction()
    object Submit : SignupAction()
}
