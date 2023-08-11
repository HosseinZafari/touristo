package com.github.hosseinzafari.touristo.presentation.screens.signup

import com.github.hosseinzafari.touristo.base.system.mvi.XAction
import com.github.hosseinzafari.touristo.base.system.mvi.XEffect
import com.github.hosseinzafari.touristo.base.system.mvi.XState
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 06/07/2023 - 2:43 PM
 * @project Touristo
 */
sealed class SignupAction : XAction {
    data class OnChangeEmail(val newText: String) : SignupAction()
    data class OnChangePassword(val newText: String) : SignupAction()
    data class OnChangeRePassword(val newText: String) : SignupAction()

    object LoginOnClick : SignupAction()
    object Submit : SignupAction()
}


sealed class SignupEffect : XEffect {
    object NavigateToLogin : SignupEffect()
    object NavigateToHome : SignupEffect()
}



data class SignupState(
    val email: String,
    val password: String,
    val replayPassword: String,
    override val effects: SignupEffect?,
    override var status: XStatus,
) : XState<SignupEffect>