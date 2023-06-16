package com.github.hosseinzafari.touristo.presentation.screens.location_make

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.hosseinzafari.touristo.L
import com.github.hosseinzafari.touristo.base.theme.TouristoTheme
import com.github.hosseinzafari.touristo.base.ui.RTL
import com.github.hosseinzafari.touristo.presentation.components.TitleBold
import com.github.hosseinzafari.touristo.presentation.components.TouristoFrame
import com.github.hosseinzafari.touristo.presentation.screens.signup.SignupAction

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 06/06/2023 - 1:09 PM
 * @project Touristo
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun LocationMakeScreen(
    viewModel: LocationMakeViewModel = hiltViewModel()
) {
    val processor = viewModel.processor
    val state = processor.subscriberState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }



    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        TouristoFrame(
            backgroundColor = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .consumeWindowInsets(it)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                TitleBold(text = "ساخت یک مقصد گردشگری")
                Spacer(modifier = Modifier.height(24.dp))


                OutlinedTextField(
                    value = state.value.name,
                    onValueChange = {
                        processor.sendAction(
                            LocationMakeAction.OnChangeNameTextField(it)
                        )
                    },
                    placeholder = {
                        Text(text = "نام مقصد")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                    isError = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.LocationCity,
                            "location name icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = state.value.imageUrl,
                    onValueChange = {
                        processor.sendAction(
                            LocationMakeAction.OnChangeImageURLTextField(it)
                        )
                    },
                    placeholder = {
                        Text(text = "لینک عکس مقصد")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Uri,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                    isError = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Image,
                            "location image icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = state.value.desc,
                    onValueChange = {
                        processor.sendAction(
                            LocationMakeAction.OnChangeDescTextField(it)
                        )
                    },
                    placeholder = {
                        Text(text = "توضیحات مقصد")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text ,
                        imeAction = ImeAction.Done

                    ),
                    singleLine = false,
                    isError = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp),
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.LocationCity,
                            "location name icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                )

                // TODO : spinner locations



                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp) ,
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "تایید" , style = MaterialTheme.typography.titleMedium)
                }

            }

        }
    }

}


@Preview
@Composable
fun LocationMakeScreenPreview() {
    TouristoTheme {
        RTL {
            LocationMakeScreen()
        }
    }
}