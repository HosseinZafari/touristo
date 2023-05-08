package com.github.hosseinzafari.touristo.presentation.screens.signup

import com.github.hosseinzafari.touristo.base.system.mvi.XEffect
import com.github.hosseinzafari.touristo.presentation.screens.login.LoginEffect

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 28/04/2023 - 5:16 PM
 * @project Touristo
 */

sealed class SignupEffect : XEffect {
    object NavigateToLogin : SignupEffect()
    object NavigateToHome : SignupEffect()
}