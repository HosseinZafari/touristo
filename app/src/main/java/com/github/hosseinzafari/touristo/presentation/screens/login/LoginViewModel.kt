package com.github.hosseinzafari.touristo.presentation.screens.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.L
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.presentation.screens.login.data.usecases.FindUserUseCase
import com.github.hosseinzafari.touristo.presentation.screens.login.data.usecases.SaveUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 27/04/2023 - 12:30 PM
 * @project Touristo
 */


@HiltViewModel
class LoginViewModel @Inject constructor(
    val saveUserInfoUseCase: SaveUserInfoUseCase ,
    val findUserUseCase: FindUserUseCase ,
) : XViewModel<LoginEffect, LoginAction, LoginState>() {
    override val processor = processor(
        initialState = LoginState(email = "", password = "", effects = null, status = XStatus.Idle),
        actionReducer = ::actionReducer,
    )


    private fun actionReducer(oldState: LoginState, action: LoginAction) {
        when (action) {
            is LoginAction.OnChangeEmail ->
                processor.setState(oldState.copy(email = action.newText))
            is LoginAction.OnChangePassword ->
                processor.setState(oldState.copy(password = action.newText))
            is LoginAction.SignupOnClick ->
                processor.setState(oldState.copy(effects = LoginEffect.NavigateToSignup))

            is LoginAction.Submit -> { // Login Operation
                Log.i("Test", "Submit ")

                if(oldState.email.isBlank()|| oldState.password.isBlank()) {
                    processor.setState(oldState.copy(
                        status = XStatus.Error(L.login_empty_email_or_password)
                    ))
                    return
                }


                viewModelScope.launch(Dispatchers.IO) {
                    processor.setState(oldState.copy(status = XStatus.Loading))
                    delay(1000L) // fake loading (simulation)
                    submit(oldState)
                }

            }
        }
    }

    private suspend fun submit(state: LoginState) {
        try {
            val user = findUserUseCase(state.email , state.password).first()
            if (user == null) {
                processor.setState(state.copy(status = XStatus.Error(L.login_wrong_email_or_password)))
                return
            }

            saveUserInfoUseCase(id = user.id!! , name = user.name!! , email = user.email!! , isLogin = true , loginAuthTime = Date().time.toString())

            processor.setState(state.copy(status = XStatus.Idle , effects = LoginEffect.NavigateToHome))
        } catch (err: Exception) {
            processor.setState(state.copy(status = XStatus.Error(L.signup_submit_error)))
        }
    }

}