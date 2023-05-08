package com.github.hosseinzafari.touristo.presentation.screens.login

import com.github.hosseinzafari.touristo.base.system.mvi.XEffect
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 28/04/2023 - 4:58 PM
 * @project Touristo
 */

sealed class LoginEffect : XEffect {
    object NavigateToSignup : LoginEffect()
    object NavigateToHome : LoginEffect()
}

