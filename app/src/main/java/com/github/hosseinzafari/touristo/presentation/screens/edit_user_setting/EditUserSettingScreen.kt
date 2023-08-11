package com.github.hosseinzafari.touristo.presentation.screens.edit_user_setting

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.base.theme.TouristoTheme
import com.github.hosseinzafari.touristo.base.ui.RTL
import com.github.hosseinzafari.touristo.presentation.components.TitleBold
import com.github.hosseinzafari.touristo.presentation.components.TouristoFrame
import com.google.accompanist.placeholder.material3.placeholder
import java.io.File

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 07/08/2023 - 2:36 PM
 * @project Touristo
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun EditUserSettingScreen(
    onNavigateToBack: () -> Unit,
    viewModel: EditUserSettingViewModel = hiltViewModel()
) {

    val processor = viewModel.processor
    val state = processor.subscriberState.collectAsState()
    val launcherImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            it?.let {
                processor.sendAction(EditUserSettingAction.OnChangeImageUri(it.toString()))
            }
        }
    )
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    processor.SubscribeEffect(
        state = state.value,
        statusBlock = {

        },
        effectsBlock = {
            when (it) {
                is EditUserSettingEffect.NavigateToBack -> {
                    onNavigateToBack()
                }
            }

            processor.setState(state.value.copy(effects = null))

        }
    )

    LaunchedEffect(key1 = 0) {
        processor.sendAction(EditUserSettingAction.GetData)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        contentWindowInsets = WindowInsets(16.dp, 16.dp, 16.dp, 16.dp)
    ) {
        TouristoFrame(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .consumeWindowInsets(it)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
                ),
            backgroundColor = Color.White
        ) {

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {

                    IconButton(
                        onClick = {
                            processor.sendAction(EditUserSettingAction.ClickOnBack)
                        }
                    ) {
                        Icon(
                            Icons.Default.ChevronRight,
                            contentDescription = "back to last screen"
                        )
                    }

                    Text(
                        text = "ویرایش حساب",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                    )

                    IconButton(
                        enabled = state.value.needToSave,
                        onClick = {
                            processor.sendAction(EditUserSettingAction.Submit)
                        }
                    ) {
                        Icon(
                            Icons.Default.Done,
                            contentDescription = "save changes"
                        )
                    }

                }

                if (state.value.status == XStatus.Loading) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )
                } else {
                    Spacer(modifier = Modifier.height(4.dp))
                }

                Spacer(modifier = Modifier.height(8.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Card(
                        modifier = Modifier
                            .size(120.dp)
                            .placeholder(
                                visible = state.value.status == XStatus.Loading && state.value.imageUri == null,
                            ),
                        shape = CircleShape,
                    ) {

                        if (state.value.imageUri == null) {
                            Icon(
                                modifier = Modifier.size(120.dp),
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "User profile image",
                            )
                        } else {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = rememberAsyncImagePainter(
                                    if (state.value.needUploadImage)
                                        Uri.parse(state.value.imageUri)
                                    else
                                        state.value.imageUri
                                ),
                                contentDescription = "User profile image",
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = {
                        launcherImage.launch("image/*")
                    }) {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = "change profile image"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                TitleBold(text = "مشخصات حساب")
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = "" + state.value.firstName,
                    onValueChange = {
                        processor.sendAction(EditUserSettingAction.OnChangeFirstName(it))
                    },
                    placeholder = {
                        Text(text = "نام")
                    },
                    singleLine = true,
                    isError = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.AccountCircle,
                            "name icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = state.value.lastName,
                    onValueChange = {
                        processor.sendAction(EditUserSettingAction.OnChangeLastName(it))
                    },
                    placeholder = {
                        Text(text = "نام خانوادگی")
                    },
                    singleLine = true,
                    isError = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.AccountCircle,
                            "family icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = "${state.value.desc}",
                    onValueChange = {
                        processor.sendAction(EditUserSettingAction.OnChangeDesc(it))
                    },
                    placeholder = {
                        Text(text = "توضیحات")
                    },
                    singleLine = false,
                    isError = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                    ),
                )

                Spacer(modifier = Modifier.height(8.dp))

            }
        }
    }
}

@Preview
@Composable
fun EditUserSettingScreenPreview() {
    TouristoTheme {
        RTL {
            EditUserSettingScreen({})
        }
    }
}