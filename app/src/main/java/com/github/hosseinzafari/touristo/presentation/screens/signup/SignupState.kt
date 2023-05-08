package com.github.hosseinzafari.touristo.presentation.screens.signup

import com.github.hosseinzafari.touristo.base.system.mvi.XState
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 28/04/2023 - 5:15 PM
 * @project Touristo
 */

data class SignupState(
    val name: String,
    val email: String,
    val password: String,
    val replayPassword: String,
    override val effects: SignupEffect?,
    override val status: XStatus,
) : XState<SignupEffect>