package com.github.hosseinzafari.touristo.presentation.screens.login

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.hosseinzafari.touristo.L
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.base.theme.*
import com.github.hosseinzafari.touristo.base.ui.RTL
import com.github.hosseinzafari.touristo.presentation.components.AuthHeader
import com.github.hosseinzafari.touristo.presentation.components.TouristoFrame
import com.queezo.app.assets.login_header
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun LoginScreen(
    onNavigateToSignup: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    loginViewModel: LoginViewModel = hiltViewModel(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
    val processor = loginViewModel.processor
    val state = processor.subscriberState.collectAsState()
    val snackbarHostState = remember {SnackbarHostState()}

    processor.SubscribeEffect(
        state = state.value ,
        statusBlock = {
            Log.i("Test" , "status happend is ${it}")
            when (it) {
                is XStatus.Error -> {
                    snackbarHostState.showSnackbar(it.msg)
                    processor.setState(state.value.copy(status = XStatus.Idle))
                }
                else -> {}
            }
        } ,
        effectsBlock = {
            // navigate to another screen
            Log.i("Test" , "effect happend is ${it}")
            when (it) {
                LoginEffect.NavigateToSignup -> {
                    onNavigateToSignup()
                }
                LoginEffect.NavigateToHome -> {
                    onNavigateToHome()
                }
            }

            processor.setState(state.value.copy(effects = null))
        }
    )


    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()  ,
        snackbarHost = { SnackbarHost(snackbarHostState) } ,
        contentWindowInsets = WindowInsets(0 , 0 , 0 ,0)
    ) {
        TouristoFrame(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .consumeWindowInsets(it)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
                )
            ,
            backgroundColor = MilkColor
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                AuthHeader(
                    drawableRes = login_header,
                    locationName = L.login_header_pin,
                    colorShadow = MilkColor,
                    top = 60.dp,
                    start = 80.dp,
                )

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 22.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column {
                        Text(
                            text = L.login_title,
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.ExtraBold,
                            style = MaterialTheme.typography.titleLarge,
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        OutlinedTextField(
                            value = state.value.email,
                            onValueChange = {
                                processor.sendAction(LoginAction.OnChangeEmail(newText = it))
                            },
                            placeholder = {
                                Text(text = L.login_email_textfield)
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email
                            ),
                            singleLine = true,
                            isError = false,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.Email,
                                    "email icon",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            })

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = state.value.password,
                            onValueChange = {
                                processor.sendAction(LoginAction.OnChangePassword(newText = it))
                            },
                            placeholder = {
                                Text(text = L.login_password_textfield)
                            },
                            singleLine = true,
                            isError = false,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            keyboardOptions = KeyboardOptions(
                                autoCorrect = false,
                                keyboardType = KeyboardType.Password
                            ),
                            visualTransformation = PasswordVisualTransformation(),
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.Password,
                                    "password icon",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            })

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            enabled = state.value.status != XStatus.Loading,
                            onClick = {
                                processor.sendAction(LoginAction.Submit)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 16.dp),
                        ) {
                            Text(
                                text = L.login_enter_button,
                                style = MaterialTheme.typography.labelLarge,
                                color = md_theme_light_secondary,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )

                            if (state.value.status == XStatus.Loading) {
                                Spacer(modifier = Modifier.width(16.dp))
                                CircularProgressIndicator(modifier = Modifier.size(12.dp) , strokeWidth = 2.dp , color = Color.Gray)
                            }
                        }

                    }

                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 32.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = L.login_is_new_user,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                modifier = Modifier.clickable {
                                    processor.sendAction(LoginAction.SignupOnClick)
                                },
                                text = L.login_register_here,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold
                            )
                        }


                        if (state.value.status == XStatus.Loading) {
                            LinearProgressIndicator(
                                modifier = Modifier.fillMaxWidth(),
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                        } else {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }


                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    TouristoTheme {
        RTL {
            LoginScreen()
        }
    }
}

