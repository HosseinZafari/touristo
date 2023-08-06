package com.github.hosseinzafari.touristo.presentation.screens.bookmark

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.base.theme.TouristoTheme
import com.github.hosseinzafari.touristo.base.ui.RTL
import com.github.hosseinzafari.touristo.presentation.components.LocationCard
import com.github.hosseinzafari.touristo.presentation.components.TitleBold
import com.github.hosseinzafari.touristo.presentation.components.TouristoFrame
import com.github.hosseinzafari.touristo.presentation.screens.home.HomeAction
import com.google.accompanist.placeholder.material3.placeholder

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 02/06/2023 - 2:38 PM
 * @project Touristo
 */

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    viewModel: BookmarkViewModel = hiltViewModel() ,
    onNavigateToBack: () -> Unit ,
    onNavigateToLocationDesc: (Int) -> Unit ,
) {

    val processor = viewModel.processor
    val state = processor.subscriberState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = 0) {
        processor.sendAction(BookmarkAction.GetData)
    }

    processor.SubscribeEffect(
        state = state.value,
        statusBlock = {

        } ,
        effectsBlock = {
            when(it) {
                is BookmarkEffect.NavigateToLocationCard -> {
                    onNavigateToLocationDesc(it.id)
                }

                is BookmarkEffect.NavigateToBack -> {
                    onNavigateToBack()
                }
            }

            processor.setState(state.value.copy(effects = null))
        } ,
    )


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        contentWindowInsets = WindowInsets(16.dp , 16.dp , 16.dp, 16.dp),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        TouristoFrame(
            backgroundColor = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(it)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp) ,
                horizontalAlignment = Alignment.Start,
            ) {

                Spacer(modifier = Modifier.height(28.dp))
                TitleBold(text = "مورد علاقه ها" , modifier = Modifier.padding(16.dp))

                Spacer(modifier = Modifier.height(16.dp))


                LazyRow {
                    if(state.value.status == XStatus.Loading) {
                        repeat(3) {
                            item {
                                Spacer(modifier = Modifier.width(16.dp))
                                LocationCard(
                                    name = "",
                                    location = "",
                                    likeCount = 0,
                                    onClick = {} ,
                                    modifier = Modifier
                                        .widthIn(min = 150.dp, max = 250.dp)
                                        .heightIn(min = 250.dp, max = 550.dp)
                                        .placeholder(visible = true , shape = RoundedCornerShape(50.dp))
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                        }
                    } else if(state.value.data.size > 0) {
                        items(state.value.data) {
                            Spacer(modifier = Modifier.width(16.dp))
                            // TODO : Location card
                            LocationCard(
                                name = it.name,
                                location = it.provinceName + " , ایران",
                                likeCount = it.likeCount ,
                                imageUri = Uri.parse(it.imageUri),
                                onClick = {
                                    processor.sendAction(BookmarkAction.ClickOnLocationCard(it.id))
                                } ,
                                modifier = Modifier
                                    .widthIn(min = 150.dp, max = 250.dp)
                                    .heightIn(min = 250.dp, max = 550.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                    } else {
                        item {
                            Spacer(modifier = Modifier.width(16.dp))
                            Text( text = "شما هیچ علاقه مندی در حال حاضر ندارید"  ,
                                style = MaterialTheme.typography.titleMedium ,
                                modifier = Modifier.fillMaxSize() ,
                                textAlign = TextAlign.Center ,
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                    }

                }
            }
        }
    }

}

@Preview()
@Composable
fun BookmarkScreenPreview() {
    TouristoTheme {
        RTL {
            BookmarkScreen(
                onNavigateToBack = {} ,
                onNavigateToLocationDesc = {} ,
            )
        }
    }
}