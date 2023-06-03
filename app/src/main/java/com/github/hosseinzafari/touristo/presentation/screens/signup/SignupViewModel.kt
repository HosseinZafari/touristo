package com.github.hosseinzafari.touristo.presentation.screens.signup

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.L
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.data_model.User
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import com.github.hosseinzafari.touristo.presentation.screens.signup.data.usecases.IsExistsUserUseCase
import com.github.hosseinzafari.touristo.presentation.screens.signup.data.usecases.SaveUserInfoUseCase
import com.github.hosseinzafari.touristo.presentation.screens.signup.data.usecases.SingupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.*
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
    val isExistsUserUseCase: IsExistsUserUseCase ,
    val saveUserInfoUseCase: SaveUserInfoUseCase ,
) : XViewModel<SignupEffect, SignupAction, SignupState>() {

    override val processor = processor(
        initialState = SignupState(
            name = "",
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
            is SignupAction.OnChangeName ->
                processor.setState(oldState.copy(name = action.newText))
            is SignupAction.OnChangeEmail ->
                processor.setState(oldState.copy(email = action.newText))
            is SignupAction.OnChangePassword ->
                processor.setState(oldState.copy(password = action.newText))
            is SignupAction.OnChangeRePassword ->
                processor.setState(oldState.copy(replayPassword = action.newText))

            is SignupAction.Submit -> {
                Log.i("Test", "Submit ")

                if (oldState.name.isBlank() || oldState.email.isBlank() || oldState.password.isBlank() || oldState.replayPassword.isBlank()) {
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
                    delay(1000L) // fake loading (simulation)
                    submit(oldState)
                }
            }
        }
    }

    private suspend fun submit(state: SignupState) {
        if(isExistsUserUseCase(state.email).first()) {
            processor.setState(state.copy(status = XStatus.Error(L.signup_user_is_exists)))
            return
        }

        try {
            val user = signupUseCase(User(id = null , name = state.name , email = state.email , password = state.password , profileUrl = null)).first()
            saveUserInfoUseCase(user.id!! , user.name!! , user.email!! , true , Date().toString())
            processor.setState(state.copy(effects = SignupEffect.NavigateToHome , status = XStatus.Idle))
        } catch (err: Exception) {
            processor.setState(state.copy(status = XStatus.Error(L.signup_submit_error)))
        }


    }
}