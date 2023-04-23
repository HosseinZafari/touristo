package com.github.hosseinzafari.touristo.presentation.screens

import android.graphics.BlurMaskFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.hosseinzafari.touristo.L
import com.github.hosseinzafari.touristo.base.theme.*
import com.github.hosseinzafari.touristo.base.ui.RTL
import com.github.hosseinzafari.touristo.base.ui.innerShadow
import com.github.hosseinzafari.touristo.presentation.components.TouristoFrame
import com.queezo.app.assets.login_header
import kotlinx.coroutines.delay
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    val stateHintLocationHeaderAnimate = remember {
        MutableTransitionState(false)
    }

    val emailState = rememberSaveable { mutableStateOf("") }
    val passwordState = rememberSaveable { mutableStateOf("") }


    LaunchedEffect(key1 = 1) {
        delay(2000)
        stateHintLocationHeaderAnimate.targetState = true
    }

    TouristoFrame(
        backgroundColor = MilkColor
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .innerShadow(
                        color = MilkColor,
                        offsetY = (-55).dp,
                        blur = 35.dp
                    )
            ) {

                Image(
                    painter = painterResource(login_header),
                    contentDescription = "Login Header",
                    contentScale = ContentScale.Crop,
                    alpha = 0.8f,
                    modifier = Modifier.fillMaxWidth(),
                )

                this@Column.AnimatedVisibility(
                    visibleState = stateHintLocationHeaderAnimate,
                    enter = expandVertically(
                        expandFrom = Alignment.Top
                    ) + expandHorizontally(expandFrom = Alignment.Start),
                    exit = shrinkVertically(
                        shrinkTowards = Alignment.Top
                    ) + shrinkHorizontally (
                        shrinkTowards = Alignment.Start
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 60.dp, start = 80.dp),
                    ) {
                        Icon(
                            Icons.Outlined.LocationOn,
                            modifier = Modifier.size(20.dp),
                            contentDescription = "location on",
                            tint = Color.White
                        )
                        Text(
                            text = L.login_header_pin,
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(2.dp),
                            color = Color.White,
                        )
                    }
                }
            }



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
                        style = MaterialTheme.typography.displaySmall,
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    OutlinedTextField(
                        value = emailState.value,
                        onValueChange = { emailState.value = it },
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
                        value = passwordState.value,
                        onValueChange = {passwordState.value = it },
                        placeholder = {
                            Text(text = L.login_password_textfield)
                        },
                        singleLine = true,
                        isError = false,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = false ,
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
                        onClick = {},
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
                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = L.login_is_new_user, style = MaterialTheme.typography.bodySmall)
                    Text(
                        modifier = Modifier.clickable {
                            Timber.tag("test").i("clicked register")
                        },
                        text = L.login_register_here,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold
                    )
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

