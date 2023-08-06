package com.github.hosseinzafari.touristo.presentation.screens.loading

import android.content.Intent
import com.github.hosseinzafari.touristo.base.system.mvi.XAction
import com.github.hosseinzafari.touristo.base.system.mvi.XEffect
import com.github.hosseinzafari.touristo.base.system.mvi.XState
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 22/07/2023 - 4:14 PM
 * @project Touristo
 */

sealed class AuthVerificationAction : XAction {
    data class HandleIntentsData(val intent: Intent) : AuthVerificationAction()
    object CheckSession : AuthVerificationAction()
    object FinishSyncing : AuthVerificationAction()
}
sealed class AuthVerificationEffect : XEffect {
    data class NavigateToHome(val msg: String): AuthVerificationEffect()
    data class NavigateToSignup(val msg: String): AuthVerificationEffect()
}

data class AuthVerificationState(
    override var status: XStatus,
    override val effects: AuthVerificationEffect?
) : XState<AuthVerificationEffect>