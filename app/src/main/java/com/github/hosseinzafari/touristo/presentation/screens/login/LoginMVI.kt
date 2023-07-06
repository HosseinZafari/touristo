package com.github.hosseinzafari.touristo.presentation.screens.login

import com.github.hosseinzafari.touristo.base.system.mvi.XAction
import com.github.hosseinzafari.touristo.base.system.mvi.XEffect
import com.github.hosseinzafari.touristo.base.system.mvi.XState
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 06/07/2023 - 2:32 PM
 * @project Touristo
 */

sealed class LoginAction : XAction {
    data class OnChangePassword(val newText: String) : LoginAction()
    data class OnChangeEmail(val newText: String) : LoginAction()
    object SignupOnClick : LoginAction()
    object Submit : LoginAction()
}

data class LoginState(
    val email: String,
    val password: String,
    override val effects: LoginEffect?,
    override var status: XStatus,
) : XState<LoginEffect>

sealed class LoginEffect : XEffect {
    object NavigateToSignup : LoginEffect()
    object NavigateToHome : LoginEffect()
}

