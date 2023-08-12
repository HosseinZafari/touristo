package com.github.hosseinzafari.touristo.presentation.screens.user_setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.github.hosseinzafari.touristo.BuildConfig
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.base.theme.MilkColor
import com.github.hosseinzafari.touristo.base.theme.TouristoTheme
import com.github.hosseinzafari.touristo.base.ui.RTL
import com.github.hosseinzafari.touristo.presentation.components.TouristoFrame
import com.github.hosseinzafari.touristo.presentation.components.UserSettingItemCard
import com.google.accompanist.placeholder.material3.placeholder

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 07/08/2023 - 10:38 AM
 * @project Touristo
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun UserSettingScreen(
    onNavigateToEditUserSetting: () -> Unit ,
    onNavigateToBack: () -> Unit ,
    onNavigateToBookmark:() -> Unit ,
    onNavigateToSignup: () -> Unit ,
    viewModel: UserSettingViewModel = hiltViewModel() ,
) {

    val processor = viewModel.processor
    val state = processor.subscriberState.collectAsState()
    processor.SubscribeEffect(
        state = state.value,
        statusBlock = {

        },
        effectsBlock = {
            when (it) {
                is UserSettingEffect.NavigateToBack -> {
                    onNavigateToBack()
                }
                is UserSettingEffect.NavigateToBookmark -> {
                    onNavigateToBookmark()
                }
                is UserSettingEffect.NavigateToEditUserSetting -> {
                    onNavigateToEditUserSetting()
                }
                is UserSettingEffect.NavigateToSignup -> {
                    onNavigateToSignup()
                }
            }
            processor.setState(state.value.copy(effects = null))

        }
    )

    LaunchedEffect(key1 = 0) {
        processor.sendAction(UserSettingAction.GetData)
    }

    val snackbarHostState = remember {
        SnackbarHostState()
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

        ) {

            Column (
                verticalArrangement = Arrangement.SpaceBetween ,
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 16.dp) ,
                        verticalAlignment = Alignment.CenterVertically ,
                    ) {

                        IconButton(
                            modifier = Modifier.weight(7f , false) ,
                            onClick = {
                                processor.sendAction(UserSettingAction.ClickOnBackButton)
                            }
                        ) {
                            Icon(Icons.Default.ChevronRight, contentDescription = "back to last screen")
                        }

                        Text(
                            text = "مشخصات حساب" ,
                            style = MaterialTheme.typography.titleMedium ,
                            modifier = Modifier.weight(3f , true) ,
                            textAlign = TextAlign.Center,
                        )
                    }


                    Spacer(modifier = Modifier.height(8.dp))

                    Row (
                        modifier = Modifier.fillMaxWidth()  ,
                        verticalAlignment = Alignment.CenterVertically ,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Card(
                            modifier = Modifier
                                .size(120.dp)
                                .placeholder(
                                    visible = state.value.status == XStatus.Loading,
                                )
                                .weight(4f, false) ,
                            shape = CircleShape ,
                        ) {
                            if (state.value.user?.profileUrl == null) {
                                Icon(
                                    modifier = Modifier.size(120.dp) ,
                                    imageVector = Icons.Default.AccountCircle,
                                    contentDescription = "User profile image" ,
                                )
                            } else {
                                Image(
                                    modifier = Modifier.fillMaxSize(),
                                    painter  = rememberAsyncImagePainter(state.value.user!!.profileUrl) ,
                                    contentDescription = "User profile image",
                                    contentScale = ContentScale.Crop
                                )
                            }

                        }

                        Column(
                            modifier = Modifier
                                .weight(6f)   ,
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Column {
                                Text(
                                    text = if(state.value.user == null) "خطا در دریافت اطلاعات" else "${state.value.user!!.name}" ,
                                    style = MaterialTheme.typography.titleMedium ,
                                    fontWeight = FontWeight.Bold ,
                                    modifier = Modifier.fillMaxWidth().placeholder(
                                        visible = state.value.status == XStatus.Loading,
                                    )
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = if(state.value.user == null) "خطا در دریافت اطلاعات" else "${state.value.user!!.family}" ,
                                    style = MaterialTheme.typography.titleMedium ,
                                    fontWeight = FontWeight.Bold ,
                                    modifier = Modifier.fillMaxWidth().placeholder(
                                        visible = state.value.status == XStatus.Loading,
                                    )
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = if(state.value.user == null) "خطا در دریافت اطلاعات" else "${state.value.user!!.email}" ,
                                    style = MaterialTheme.typography.titleSmall ,
                                    modifier = Modifier.fillMaxWidth().placeholder(
                                        visible = state.value.status == XStatus.Loading,
                                    )
                                )
                            }

                            TextButton(onClick = {
                                processor.sendAction(UserSettingAction.ClickOnEditProfile)
                            }) {
                                Text(
                                    text = "ویرایش حساب" ,
                                    style = MaterialTheme.typography.labelMedium,
                                )
                            }
                        }

                    }


                    Spacer(modifier = Modifier.height(16.dp))

                    Divider(modifier = Modifier.fillMaxWidth() , color = Color.LightGray)
                    Spacer(modifier = Modifier.height(16.dp))
                    UserSettingItemCard(
                        imageVector = Icons.Outlined.BookmarkBorder ,
                        itemName = "مورد علاقه ها" ,
                    ) {
                        processor.sendAction(UserSettingAction.ClickOnBookmarkItem)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    UserSettingItemCard(
                        imageVector = Icons.Outlined.Logout ,
                        itemName = "خروج از حساب" ,
                    ) {
                        processor.sendAction(UserSettingAction.ClickOnLogoutItem)
                    }

                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp) ,
                    text = "نسخه : ${BuildConfig.VERSION_NAME}" ,
                    color = Color.LightGray ,
                    textAlign = TextAlign.Center ,
                )
            }




        }
    }


}

@Preview(showBackground = true)
@Composable
fun UserSettingScreenPreview() {
    TouristoTheme {
        RTL {
            UserSettingScreen({} , {} , {} , {})
        }
    }
}