package com.github.hosseinzafari.touristo.presentation.screens.signup

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.L
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.local.usecases.SaveUserUseCase
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import com.github.hosseinzafari.touristo.presentation.screens.signup.data.usecases.SingupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.exceptions.RestException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 28/04/2023 - 5:46 PM
 * @project Touristo
 */


@HiltViewModel
class SignupViewModel @Inject constructor(
    val signupUseCase: SingupUseCase ,
    val saveUserUseCase: SaveUserUseCase ,
) : XViewModel<SignupEffect, SignupAction, SignupState>() {

    override val processor = processor(
        initialState = SignupState(
            email = "",
            password = "",
            replayPassword = "",
            effects = null,
            status = XStatus.Idle
        ),
        actionReducer = ::actionReducer ,
    )


    private fun actionReducer(oldState: SignupState, action: SignupAction)  {
        when (action) {
            is SignupAction.LoginOnClick ->
                processor.setState(oldState.copy(effects = SignupEffect.NavigateToLogin))
            is SignupAction.OnChangeEmail ->
                processor.setState(oldState.copy(email = action.newText))
            is SignupAction.OnChangePassword ->
                processor.setState(oldState.copy(password = action.newText))
            is SignupAction.OnChangeRePassword ->
                processor.setState(oldState.copy(replayPassword = action.newText))

            is SignupAction.Submit -> {
                Log.i("Test", "Submit ")

                if ( oldState.email.isBlank() || oldState.password.isBlank() || oldState.replayPassword.isBlank()) {
                    processor.setState(oldState.copy(status = XStatus.Error(L.signup_empty_edit_texts)))
                    return
                }

                if (oldState.password.count() < 5) {
                    processor.setState(oldState.copy(status = XStatus.Error(L.signup_password_less_length)))
                    return
                }

                if (!oldState.password.equals(oldState.replayPassword)) {
                    processor.setState(oldState.copy(status = XStatus.Error(L.signup_password_not_equals)))
                    return
                }

                viewModelScope.launch(Dispatchers.IO) {
                    processor.setState(oldState.copy(status = XStatus.Loading))
                    submit(oldState)
                }
            }
        }
    }

    private suspend fun submit(state: SignupState) {
        try {
            signupUseCase(state.email, state.password )
            processor.setState(state.copy(status = XStatus.Idle))
        } catch (exc: RestException) {
            Log.i("Test", "signupSubmit exception: $exc")
            processor.setState(state.copy(status = XStatus.Error(L.signup_submit_checking_email)))
        } catch (exc: Exception) {
            Log.i("Test", "signupSubmit exception: $exc")
            processor.setState(state.copy(status = XStatus.Error(L.signup_submit_error)))
        }
    }
}