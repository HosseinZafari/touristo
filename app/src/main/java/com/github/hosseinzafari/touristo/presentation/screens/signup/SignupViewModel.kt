package com.github.hosseinzafari.touristo.presentation.screens.signup

import android.util.Log
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 28/04/2023 - 5:46 PM
 * @project Touristo
 */


@HiltViewModel
class SignupViewModel @Inject constructor() : XViewModel<SignupEffect, SignupAction, SignupState>() {

    override val processor = processor(
        initialState = SignupState(
            name = "",
            email = "",
            password = "",
            replayPassword = "",
            effects = null,
            status = XStatus.Idle
        ),
        actionReducer = ::actionReducer
    )


    private fun actionReducer(oldState: SignupState, action: SignupAction): Unit = when (action) {
        is SignupAction.LoginOnClick -> {
            Log.i("Test", "LoginOnClick ")
            processor.setState(oldState.copy(effects = SignupEffect.NavigateToLogin))
        }
        is SignupAction.OnChangeName -> {
            Log.i("Test", "OnChangeName ")
            processor.setState(oldState.copy(name = action.newText))
        }
        is SignupAction.OnChangeEmail -> {
            Log.i("Test", "OnChangeEmail ")
            processor.setState(oldState.copy(email = action.newText))
        }
        is SignupAction.OnChangePassword -> {
            Log.i("Test", "OnChangePassword ")
            processor.setState(oldState.copy(password = action.newText))
        }
        is SignupAction.OnChangeRePassword -> {
            Log.i("Test", "OnChangeRePassword ")
            processor.setState(oldState.copy(replayPassword = action.newText))
        }
        is SignupAction.Submit -> {
            Log.i("Test", "Submit ")
            processor.setState(oldState.copy(effects = SignupEffect.NavigateToHome))
        }
    }
}