package com.github.hosseinzafari.touristo.presentation.screens.login

import com.github.hosseinzafari.touristo.base.system.mvi.XAction

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 28/04/2023 - 4:58 PM
 * @project Touristo
 */

sealed class LoginAction : XAction {
    data class OnChangePassword(val newText: String) : LoginAction()
    data class OnChangeEmail(val newText: String) : LoginAction()
    object SignupOnClick : LoginAction()
    object Submit : LoginAction()
}