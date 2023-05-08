package com.github.hosseinzafari.touristo.presentation.screens.login

import com.github.hosseinzafari.touristo.base.system.mvi.XState
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 28/04/2023 - 4:58 PM
 * @project Touristo
 */


data class LoginState(
    val email: String,
    val password: String,
    override val effects: LoginEffect?,
    override val status: XStatus,
) : XState<LoginEffect>