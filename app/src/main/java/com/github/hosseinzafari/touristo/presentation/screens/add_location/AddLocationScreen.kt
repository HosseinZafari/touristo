package com.github.hosseinzafari.touristo.presentation.screens.add_location

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.base.theme.TouristoTheme
import com.github.hosseinzafari.touristo.base.ui.RTL
import com.github.hosseinzafari.touristo.core.data.data_model.categories
import com.github.hosseinzafari.touristo.core.data.data_model.provinceData
import com.github.hosseinzafari.touristo.presentation.components.TitleBold
import com.github.hosseinzafari.touristo.presentation.components.TouristoFrame
import com.github.hosseinzafari.touristo.presentation.screens.location_description.LocationDescEffect

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 24/06/2023 - 6:57 PM
 * @project Touristo
 */


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AddLocationScreen(
    viewModel: AddLocationViewModel = hiltViewModel() ,
    onNavigateToHome: () -> Unit ,
) {

    val processor = viewModel.processor
    val state = processor.subscriberState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }


    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            processor.sendAction(AddLocationAction.OnChangePicture(it))
        }
    )

    var showDialogProvince by remember { mutableStateOf(false) }
    var showDialogCategory by remember { mutableStateOf(false) }

    processor.SubscribeEffect(
        state = state.value,
        statusBlock = {
            val status = state.value.status
            when (status) {
                is XStatus.Error -> {
                    snackbarHostState.showSnackbar(status.msg)
                    processor.setState(state.value.copy(status = XStatus.Idle))
                }
                else -> {}
            }
        } ,
        effectsBlock = {
            when(it) {
                is AddLocationEffect.NavigateToHome -> {
                    onNavigateToHome()
                }
            }
            processor.setState(state.value.copy(effects = null))
        },
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        contentWindowInsets = WindowInsets(16.dp , 16.dp , 16.dp,16.dp),
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
                modifier = Modifier.padding(4.dp) ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                TitleBold(text = "اضافه نمودن یک مکان گردشگری")
                Spacer(modifier = Modifier.height(16.dp))


                OutlinedTextField(
                    value = state.value.name,
                    onValueChange = {
                        processor.sendAction(AddLocationAction.OnChangeName(it))
                    },
                    placeholder = {
                        Text(text = "نام مکان گردشگری")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text ,
                        imeAction = ImeAction.Done ,
                    ),
                    singleLine = true,
                    isError = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Info,
                            "info icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = state.value.description,
                    onValueChange = {
                        processor.sendAction(AddLocationAction.OnChangeDesc(it))
                    },
                    placeholder = {
                        Text(text = "توضیحات مکان گردشگری")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text ,
                        imeAction = ImeAction.Done ,
                    ),
                    singleLine = true,
                    isError = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Info,
                            "info icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))



                Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp) ,
                        shape = RoundedCornerShape(35.dp) ,
                        colors = CardDefaults.cardColors(Color.LightGray) ,
                        onClick = {
                            galleryLauncher.launch("image/*")
                        }
                    ) {
                        if(state.value.pictureUrl == null) {
                            Text(modifier = Modifier.fillMaxSize() , text = "انتخاب تصویر از گالری" , textAlign = TextAlign.Center)
                        } else {
                            Image(
                                painter = rememberAsyncImagePainter(state.value.pictureUrl) ,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(50.dp)
                                    ) ,
                                contentScale = ContentScale.Crop ,
                                contentDescription = null ,
                            )
                        }
                    }
                Spacer(modifier = Modifier.height(8.dp))


                if (showDialogCategory) {
                    Dialog(onDismissRequest = { showDialogCategory = !showDialogCategory }) {
                        LazyColumn (
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(categories) {
                                Card(
                                    modifier = Modifier.fillMaxWidth() ,
                                    colors = CardDefaults.cardColors(containerColor = Color.White),
                                    onClick = {
                                        processor.sendAction(AddLocationAction.OnChangeCategory(it))
                                        showDialogCategory = !showDialogCategory
                                    }
                                ) {
                                    Text(text = it.title  , modifier = Modifier.padding(18.dp).fillMaxWidth() , textAlign = TextAlign.Center)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                            }
                        }
                    }
                }

                Button(
                    modifier = Modifier.fillMaxWidth() ,
                    onClick = {
                        showDialogCategory = !showDialogCategory
                    }
                ) {
                    if (state.value.category == null) {
                        Text(text = "انتخاب دسته بندی مورد نظر")
                    } else {
                        Text(text = state.value.category!!.title)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))


                if (showDialogProvince) {
                    Dialog(onDismissRequest = { showDialogProvince = !showDialogProvince }) {
                        LazyColumn (
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(provinceData) {
                                Card(
                                    modifier = Modifier.fillMaxWidth() ,
                                    colors = CardDefaults.cardColors(containerColor = Color.White),
                                    onClick = {
                                        processor.sendAction(AddLocationAction.OnChangeProvince(it))
                                        showDialogProvince = !showDialogProvince
                                    }
                                ) {
                                    Text(text = it.name  , modifier = Modifier.padding(18.dp).fillMaxWidth() , textAlign = TextAlign.Center)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                            }
                        }
                    }
                }

                Button(
                    modifier = Modifier.fillMaxWidth() ,
                    onClick = {
                        showDialogProvince = !showDialogProvince
                    }
                ) {
                    if (state.value.province == null) {
                        Text(text = "انتخاب استان مورد نظر")
                    } else {
                        Text(text = state.value.province!!.name)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    modifier = Modifier.fillMaxWidth() ,
                    onClick = {
                        processor.sendAction(AddLocationAction.Submit)
                    }
                ) {
                    Text(text = "تایید")
                }

                Spacer(modifier = Modifier.height(16.dp))

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

@Preview
@Composable
fun AddLocationScreenPreview() {
    TouristoTheme {
        RTL {
            AddLocationScreen( onNavigateToHome = {}
            )
        }
    }
}


