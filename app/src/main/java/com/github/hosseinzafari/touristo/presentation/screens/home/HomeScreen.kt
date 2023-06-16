package com.github.hosseinzafari.touristo.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import com.github.hosseinzafari.touristo.core.data.data_model.categories
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
    onNavigateToLocationDesc: (Int) -> Unit , 
    onNavigateToSearch: (Int) -> Unit ,
) {
    val processor = viewModel.processor
    val state = processor.subscriberState.collectAsState()

    processor.SubscribeEffect(
        state = state.value,
        statusBlock = {
        },
        effectsBlock = {
            when(it) {
                is HomeEffect.NavigateToLocationDesc -> {
                    onNavigateToLocationDesc(it.locationID)
                }

                is HomeEffect.NavigateToSearch -> {
                    onNavigateToSearch(it.locationID)
                }
            }
        }
    )

    LaunchedEffect(key1 = 0) {
        processor.sendAction(HomeAction.GetData(0))
    }


    val tabsIndexState = remember { mutableStateOf(0) }
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
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
                }

                ScrollableTabRow(
                    selectedTabIndex = tabsIndexState.value,
                    contentColor = Color.DarkGray,
                    divider = {},
                    indicator = {
                        Box {}
                    }

                ) {
                    categories.forEachIndexed { index, value ->

                        Tab(
                            modifier = Modifier.drawWithContent {
                                drawContent()
                                if (tabsIndexState.value == index) {
                                    drawLine(
                                        color = Color.DarkGray,
                                        start = Offset(size.width, 0f),
                                        end = Offset(size.width / 1.5f, 0f),
                                        strokeWidth = 25f
                                    )
                                }
                            },
                            selected = tabsIndexState.value == index,
                            onClick = {
                                Log.i("Test", "tab clicked $index")
                                tabsIndexState.value = index
                                processor.sendAction(HomeAction.ChangeCurrentTab(tabsIndexState.value))
                            },
                            text = {
                                Text(
                                    text = value.title,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = if (tabsIndexState.value == index) FontWeight.Bold else FontWeight.Light
                                )
                            }
                        )
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
                                LocationCard(
                                    resId = it.resID,
                                    name = it.name,
                                    location = it.location.name + " , ایران",
                                    likeCount = it.likeCount,
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
                        if(state.value.status == XStatus.Loading) {
                            repeat(3) {
                                item {
                                    BestDestinationCard(
                                        modifier = Modifier.height(100.dp).placeholder(
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
                                        resId = it.resID,
                                        province = it.location.name,
                                        country = "ایران",
                                        onClick = {
                                            processor.sendAction(
                                                HomeAction.ClickOnMostDestinationCard(
                                                    it.location.id
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
            HomeScreen(onNavigateToLocationDesc = {} , onNavigateToSearch = {})
        }
    }
}

