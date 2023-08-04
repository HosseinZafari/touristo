package com.github.hosseinzafari.touristo.presentation.screens.location_description

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.base.theme.TouristoTheme
import com.github.hosseinzafari.touristo.base.ui.RTL
import com.github.hosseinzafari.touristo.presentation.components.TitleBold
import com.github.hosseinzafari.touristo.presentation.components.TouristoFrame
import com.google.accompanist.placeholder.material3.placeholder

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 04/06/2023 - 3:44 PM
 * @project Touristo
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun LocationDescScreen(
    viewModel: LocationDescViewModel = hiltViewModel() ,
    descId: Int ,
    onNavigateToBack: () -> Unit ,
    onNavigateToComment: (Int) -> Unit ,
) {
    val processor = viewModel.processor
    val state = processor.subscriberState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = 0) {
        processor.sendAction(LocationDescAction.GetLocationDescData(descId))
    }


    processor.SubscribeEffect(
        state = state.value,
        statusBlock = {
        } ,
        effectsBlock = {
            when(it) {
                is LocationDescEffect.NavigateToBack -> {
                    onNavigateToBack()
                }

                is LocationDescEffect.NavigateToComment-> {
                    onNavigateToComment(it.descId)
                }
            }

            processor.setState(state.value.copy(effects = null))

        },
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        contentWindowInsets = WindowInsets(0 , 0 , 0,0),
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

            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomEnd = 64.dp,
                                bottomStart = 64.dp
                            )
                        ),
                    contentAlignment = Alignment.TopCenter ,
                ) {
                    if(state.value.status == XStatus.Loading) {
                        Box(
                            modifier = Modifier.fillMaxSize().placeholder(
                                visible = true ,
                                shape = RoundedCornerShape(4.dp)
                            ) ,
                        )
                    } else if(state.value.data != null) {
                        Image(
                            modifier = Modifier.fillMaxSize() ,
                            painter =  rememberAsyncImagePainter(state.value.data!!.imageUri) ,
                           contentDescription = "cover image" ,
                            contentScale = ContentScale.Crop
                        )
                    }

                    Column (
                        modifier = Modifier.fillMaxSize() ,
                        verticalArrangement = Arrangement.SpaceBetween ,
                        horizontalAlignment = Alignment.CenterHorizontally ,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal  = 16.dp , vertical = 52.dp) ,
                            horizontalArrangement = Arrangement.SpaceBetween ,
                            verticalAlignment = Alignment.Top ,
                        ) {
                            Column (
                                modifier = Modifier.background(
                                    color = Color.White.copy(alpha = 0.7f) ,
                                    shape = RoundedCornerShape(50.dp) ,
                                )
                            ) {
                                IconButton(
                                    enabled = state.value.status != XStatus.Loading && state.value.liked != null ,
                                    onClick = {
                                    processor.sendAction(LocationDescAction.ClickOnLikeButton )
                                }) {
                                    Icon(imageVector = if(state.value.liked != null && state.value.liked!!) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder , tint = Color.Gray,  contentDescription = null)
                                }

                                IconButton(
                                    enabled = state.value.status != XStatus.Loading && state.value.liked != null ,
                                    onClick = {
                                    processor.sendAction(LocationDescAction.ClickOnBookmarkButton )
                                }) {
                                    Icon(imageVector = if(state.value.bookmarked != null && state.value.bookmarked!!) Icons.Outlined.Bookmark  else Icons.Outlined.BookmarkBorder , tint = Color.Gray, contentDescription = "bookamrk")
                                }

                                IconButton(
                                    enabled = state.value.status != XStatus.Loading ,
                                    onClick = {
                                    processor.sendAction(LocationDescAction.ClickOnCommentButton )
                                }) {
                                    Icon(imageVector = Icons.Outlined.Comment , tint = Color.Gray,contentDescription = "bookamrk")
                                }
                            }


                            IconButton(
                                modifier = Modifier.background(
                                    color = Color.White.copy(alpha = 0.7f) ,
                                    shape =  CircleShape
                                ) ,
                                onClick = { processor.sendAction(LocationDescAction.ClickOnBackButton ) }) {
                                Icon(imageVector = Icons.Outlined.ChevronLeft , tint = Color.Gray ,  contentDescription = null)
                            }
                        }

                        Row(
                            modifier = Modifier
                                .background(
                                    color = Color.White.copy(alpha = 0.3f),
                                    shape = RoundedCornerShape(2.dp)
                                )
                                .fillMaxWidth()
                                .padding(16.dp) ,
                            horizontalArrangement = Arrangement.SpaceEvenly ,
                            verticalAlignment = Alignment.CenterVertically ,
                        ) {
                            if(state.value.status == XStatus.Loading) {
                                Box(
                                    modifier = Modifier.padding(8.dp).placeholder(
                                        visible = true ,
                                        shape = RoundedCornerShape(4.dp)
                                    ).weight(1f)
                                )

                                Spacer(modifier = Modifier.weight(3f))

                                Box(
                                    modifier = Modifier.padding(8.dp).placeholder(
                                        visible = true ,
                                        shape = RoundedCornerShape(4.dp)
                                    ).weight(1f)
                                )

                            }  else if(state.value.data != null) {
                                Text(
                                    text = state.value.data!!.name ,
                                    color = Color.White ,
                                    style = MaterialTheme.typography.titleLarge
                                )


                                Text(
                                    text =   state.value.data!!.provinceName + " , ایران",
                                    color = Color.White ,
                                    style = MaterialTheme.typography.titleSmall
                                )
                            }

                        }
                    }

                }


                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    if(state.value.status == XStatus.Loading) {
                        Box(
                            modifier = Modifier.size(120.dp , 16.dp).placeholder(
                                visible = true ,
                                shape = RoundedCornerShape(4.dp)
                            ),
                        )

                        Spacer(modifier = Modifier.height(4.dp))
                        TitleBold(text = "توضیحات")
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier.fillMaxSize().placeholder(
                                visible = true ,
                                shape = RoundedCornerShape(50.dp)
                            ) ,
                        )
                    } else if(state.value.data != null) {
                        Text(
                            text = "تعداد لایک ${state.value.data!!.likeCount}" ,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        TitleBold(text = "توضیحات")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = state.value.data!!.desc ,
                            style = MaterialTheme.typography.bodyMedium ,
                            textAlign = TextAlign.Justify ,
                            lineHeight = 35.sp ,
                            letterSpacing = 3.sp ,
                        )
                    }
                }
            }

        }
    }
}


@Preview()
@Composable
fun LocationDescScreenPreview() {
    TouristoTheme {
        RTL {
            LocationDescScreen(descId = 0 , onNavigateToBack =  {} , onNavigateToComment =  {})
        }
    }
}