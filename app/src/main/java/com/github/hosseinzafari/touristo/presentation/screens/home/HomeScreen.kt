package com.github.hosseinzafari.touristo.presentation.screens.home

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.base.theme.TouristoTheme
import com.github.hosseinzafari.touristo.base.ui.RTL
import com.github.hosseinzafari.touristo.core.data.dto.provinceData
import com.github.hosseinzafari.touristo.presentation.components.*
import com.google.accompanist.placeholder.material3.placeholder
import com.queezo.app.assets.card_1_2

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 05/05/2023 - 3:12 PM
 * @project Touristo
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToLocationDesc: (Int) -> Unit,
    onNavigateToSearch: (Int) -> Unit,
    onNavigateToBookmark: () -> Unit,
    onNavigateToAddLocation: () -> Unit,
) {
    val processor = viewModel.processor
    val state = processor.subscriberState.collectAsState()

    processor.SubscribeEffect(
        state = state.value,
        statusBlock = {
        },
        effectsBlock = {
            when (it) {
                is HomeEffect.NavigateToLocationDesc -> {
                    onNavigateToLocationDesc(it.locationID)
                }

                is HomeEffect.NavigateToSearch -> {
                    onNavigateToSearch(it.locationID)
                }

                is HomeEffect.NavigateToBookmark -> {
                    onNavigateToBookmark()
                }

                is HomeEffect.NavigateToAddLocation -> {
                    onNavigateToAddLocation()
                }

            }

            processor.setState(state.value.copy(effects = null))
        }
    )

    LaunchedEffect(key1 = 0) {
        processor.sendAction(HomeAction.GetData(0))
    }


    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                processor.sendAction(HomeAction.ClickOnFloatingActionButton)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add a location")
            }
        }
    ) {
        TouristoFrame(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .consumeWindowInsets(it)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
                )
        ) {

            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TitleExtraBold(
                        modifier = Modifier.padding(16.dp), text = "کاوش"
                    )
                    Row {
                        Box(
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .background(color = Color(0xffeeeeee), shape = CircleShape),
                        ) {
                            IconButton(onClick = {
                                processor.sendAction(HomeAction.ClickOnSearchButton(0))
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search Box"
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Box(
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .background(color = Color(0xffeeeeee), shape = CircleShape),
                        ) {
                            IconButton(onClick = {
                                processor.sendAction(HomeAction.ClickOnBookmarkButton)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Bookmark Box"
                                )
                            }
                        }
                    }
                }

                if(state.value.currentCategory != null) {
                    ScrollableTabRow(
                        selectedTabIndex = state.value.currentCategory!!.id,
                        contentColor = Color.DarkGray,
                        divider = {},
                        indicator = {
                            Box {}
                        }
                    ) {
                        val categories = state.value.categoryData
                        if (categories.size > 0) {
                            categories.forEachIndexed { index, value ->
                                Tab(
                                    modifier = Modifier.drawWithContent {
                                        drawContent()
                                        if (state.value.currentCategory!!.id == value.id) {
                                            drawLine(
                                                color = Color.DarkGray,
                                                start = Offset(size.width, 0f),
                                                end = Offset(size.width / 1.5f, 0f),
                                                strokeWidth = 25f
                                            )
                                        }
                                    },
                                    selected = state.value.currentCategory!!.id == value.id,
                                    onClick = {
                                        processor.sendAction(HomeAction.ChangeCurrentTab(value))
                                    },
                                    text = {
                                        Text(
                                            text = value.title,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = if (state.value.currentCategory!!.id == value.id) FontWeight.Bold else FontWeight.Light
                                        )
                                    }
                                )
                            }
                        }
                    }
                } else {
                    ScrollableTabRow(
                        selectedTabIndex = 0,
                        contentColor = Color.DarkGray,
                        divider = {},
                        indicator = {
                            Box {}
                        }
                    ) {
                        repeat(4) {
                            Tab(
                                modifier = Modifier.placeholder(
                                    visible = true,
                                    shape = RoundedCornerShape(4.dp)
                                ),
                                selected = true,
                                onClick = {
                                },
                                text = {
                                    Text(
                                        text = "",
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        style = MaterialTheme.typography.titleLarge,
                                    )
                                }
                            )

                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))


                LazyRow {
                    if (state.value.status == XStatus.Loading) {
                        repeat(3) {
                            item {
                                Spacer(modifier = Modifier.width(16.dp))
                                LocationCard(
                                    modifier = Modifier
                                        .widthIn(min = 200.dp, max = 280.dp)
                                        .heightIn(min = 300.dp, max = 500.dp)
                                        .placeholder(
                                            visible = true,
                                            shape = RoundedCornerShape(50.dp)
                                        ),
                                    resId = card_1_2,
                                    name = "",
                                    location = "",
                                    likeCount = 0,
                                    onClick = { }
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                        }
                    } else {
                        if (state.value.locationData.size > 0) {
                            items(state.value.locationData) {
                                Spacer(modifier = Modifier.width(16.dp))
                                // TODO : LOCATION CARD
                                LocationCard(
                                    name = it.name,
                                    location = it.provinceName + " , ایران",
                                    likeCount = it.likeCount,
                                    imageUri = Uri.parse(it.imageUri),
                                    onClick = {
                                        processor.sendAction(HomeAction.ClickOnCardItem(it.id))
                                    }
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                        } else {
                            item {
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(text = "رکوردی با این فیلتر وجود ندارد.")
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                        }
                    }

                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    TitleBold(
                        text = "مقصد های برتر"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow {
                        if (state.value.status == XStatus.Loading) {
                            repeat(3) {
                                item {
                                    BestDestinationCard(
                                        modifier = Modifier
                                            .height(100.dp)
                                            .placeholder(
                                                visible = true,
                                                shape = RoundedCornerShape(50.dp)
                                            ),
                                        resId = card_1_2,
                                        province = "",
                                        country = "",
                                        onClick = {}
                                    )
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                            }
                        } else {
                            if (state.value.destinationData.size > 0) {
                                items(state.value.destinationData) {
                                    BestDestinationCard(
                                        province = it.provinceName,
                                        country = "ایران",
                                        imageUri = Uri.parse(it.imageUri) ,
                                        onClick = {
                                            processor.sendAction(
                                                HomeAction.ClickOnMostDestinationCard(
                                                    provinceData.filter {province ->
                                                        province.name == it.provinceName
                                                    }.first().id
                                                )
                                            )
                                        }
                                    )
                                    Spacer(modifier = Modifier.width(16.dp))

                                }
                            } else {
                                item {
                                    Text(text = "مقصد در حال حاضر وجود ندارد")
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                            }
                        }
                    }
                }

            }

        }
    }
}

@Preview()
@Composable
fun ScreenHomePreview() {
    TouristoTheme {
        RTL {
            HomeScreen(onNavigateToLocationDesc = {},
                onNavigateToSearch = {},
                onNavigateToBookmark = {},
                onNavigateToAddLocation = {})
        }
    }
}

