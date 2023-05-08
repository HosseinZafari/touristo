package com.github.hosseinzafari.touristo.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.hosseinzafari.touristo.L
import com.github.hosseinzafari.touristo.ChangeSystemBarsColor
import com.github.hosseinzafari.touristo.base.theme.MilkColor
import com.github.hosseinzafari.touristo.base.theme.TouristoTheme
import com.github.hosseinzafari.touristo.base.theme.md_theme_light_secondary
import com.github.hosseinzafari.touristo.base.ui.RTL
import com.github.hosseinzafari.touristo.presentation.components.AuthHeader
import com.github.hosseinzafari.touristo.presentation.components.TouristoFrame
import com.github.hosseinzafari.touristo.presentation.screens.signup.SignupViewModel
import com.queezo.app.assets.signup_header
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.hosseinzafari.touristo.presentation.screens.signup.SignupAction
import com.github.hosseinzafari.touristo.presentation.screens.signup.SignupEffect

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 24/04/2023 - 12:08 PM
 * @project Touristo
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    onNavigateToLogin: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    signupViewModel: SignupViewModel = viewModel() ,
) {

    val processor = signupViewModel.processor
    val state = processor.subscriberState.collectAsState()


    LaunchedEffect(key1 = state.value.effects) {
        when(state.value.effects) {
            is SignupEffect.NavigateToLogin -> {
                onNavigateToLogin()
            }

            else -> {}
        }
    }


    TouristoFrame(
        backgroundColor = MilkColor,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            AuthHeader(
                drawableRes = signup_header,
                locationName = L.signup_header_pin,
                colorShadow = MilkColor,
                start = 180.dp     ,
                top = 40.dp ,
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 22.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    Text(
                        text = L.signup_title,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.titleLarge,
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    OutlinedTextField(
                        value = state.value.name,
                        onValueChange = { processor.sendAction(SignupAction.OnChangeName(it))},
                        placeholder = {
                            Text(text = L.signup_name_textfield)
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        ),
                        singleLine = true,
                        isError = false,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.AccountBox,
                                "account icon",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        })

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = state.value.email,
                        onValueChange = {  processor.sendAction(SignupAction.OnChangeEmail(it))},
                        placeholder = {
                            Text(text = L.signup_email_textfield)
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
                        onValueChange = { processor.sendAction(SignupAction.OnChangePassword(it))},
                        placeholder = {
                            Text(text = L.signup_password_textfield)
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

                    OutlinedTextField(
                        value = state.value.replayPassword,
                        onValueChange = { processor.sendAction(SignupAction.OnChangeRePassword(it))},
                        placeholder = {
                            Text(text = L.signup_replay_password_textfield)
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
                        onClick = {
                            processor.sendAction(SignupAction.Submit)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 16.dp),
                    ) {
                        Text(
                            text = L.signup_enter_button,
                            style = MaterialTheme.typography.labelLarge,
                            color = md_theme_light_secondary,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = L.signup_is_old_user, style = MaterialTheme.typography.bodySmall)
                    Text(
                        modifier = Modifier.clickable {
                            processor.sendAction(SignupAction.LoginOnClick)
                        },
                        text = L.signup_login_here,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }
    }
}


@Composable
@Preview
fun SignupScreenPreview() {
    TouristoTheme {
        ChangeSystemBarsColor()
        RTL {
            SignupScreen()
        }
    }
}