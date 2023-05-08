package com.github.hosseinzafari.touristo.presentation.screens.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.presentation.screens.login.data.usecases.FindUserUseCase
import com.github.hosseinzafari.touristo.presentation.screens.login.data.usecases.SaveUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
            is LoginAction.OnChangeEmail -> {
                Log.i("Test", "OnChangeEmail ")
                processor.setState(oldState.copy(email = action.newText))
            }

            is LoginAction.OnChangePassword -> {
                Log.i("Test", "OnChangePassword ")
                processor.setState(oldState.copy(password = action.newText))
            }

            is LoginAction.Submit -> { // Login Operation
                Log.i("Test", "Submit ")
                if(oldState.email.isBlank()|| oldState.password.isBlank()) {
                    processor.setState(oldState.copy(
                        status = XStatus.Error("لطفا ایمیل یا رمز عبور را خالی وارد نکنید.")
                    ))
                }

                processor.setState(oldState.copy(
                    status = XStatus.Loading
                ))

                viewModelScope.launch(Dispatchers.IO) {
                    val user = findUserUseCase(oldState.email , oldState.password).first()
                    if (user == null) {
                        processor.setState(oldState.copy(
                            status = XStatus.Error("رمز عبور یا ایمیل مورد نظر شما صحیح نیست!")
                        ))

                        return@launch
                    }


                    saveUserInfoUseCase(
                        id = user.id!! ,
                        name = user.name!! ,
                        email = user.email!! ,
                        isLogin = true ,
                        loginAuthTime = Date().time.toString()
                    )

                    Log.i("Test" , "it ok ${user}")
                    processor.setState(oldState.copy(
                        status = XStatus.Idle ,
                        effects = LoginEffect.NavigateToHome
                    ))

                }

            }

            is LoginAction.SignupOnClick -> {
                Log.i("Test", "SignupOnClick ")
                processor.setState(oldState.copy(effects = LoginEffect.NavigateToSignup))
            }
        }
    }


}