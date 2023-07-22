package com.github.hosseinzafari.touristo.presentation.screens.Loading

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.handleDeeplinks
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 22/07/2023 - 4:13 PM
 * @project Touristo
 */

@HiltViewModel()
class AuthVerificationViewModel @Inject constructor(
    val client: SupabaseClient
) :
    XViewModel<AuthVerificationEffect, AuthVerificationAction, AuthVerificationState>() {
    override val processor = processor(
        initialState = AuthVerificationState(
            status = XStatus.Loading,
            effects = null
        ),
        actionReducer = ::actionReducer
    )

    private fun actionReducer(oldState: AuthVerificationState, action: AuthVerificationAction) {
        processor.setState(oldState.copy(status = XStatus.Loading))
        when (action) {
            is AuthVerificationAction.HandleIntentsData -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val intent = action.intent
                    val action = intent.action
                    val data = intent.data
                    Log.i("Test", "onNewIntent() intent action " + action)
                    Log.i("Test", "onNewIntent() intent data " + data)

                    client.handleDeeplinks(intent) {
                        Log.i("Test", "handleDeeplinks successfully " + it)
                        processor.setState(
                            oldState.copy(
                                status = XStatus.Idle,
                                effects = AuthVerificationEffect.NavigateToHome("شما با موفقیت وارد شدید.")
                            )
                        )

                    }

                }
            }

            is AuthVerificationAction.FinishSyncing -> {
                processor.setState(
                    oldState.copy(
                        status = XStatus.Idle,
                        effects = AuthVerificationEffect.NavigateToSignup("خطای ناشناخته")
                    )
                )
            }

            is AuthVerificationAction.CheckSession -> {
                viewModelScope.launch(Dispatchers.IO) {
                    if (client.gotrue.loadFromStorage()) {
                        Log.i("Test", "succeed  true")
                        processor.setState(
                            oldState.copy(
                                status = XStatus.Idle,
                                effects = AuthVerificationEffect.NavigateToHome("شما با موفقیت وارد شدید.")
                            )
                        )
                    } else {
                        Log.i("Test", "succeed  false")
                        processor.setState(
                            oldState.copy(
                                status = XStatus.Idle,
                                effects = AuthVerificationEffect.NavigateToSignup("ایمیل یا لینک مورد نظر اشتباه یا منقضی شده است.")
                            )
                        )
                    }


                }
            }
        }
    }

}