package com.github.hosseinzafari.touristo.presentation.screens.home

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.hosseinzafari.touristo.base.theme.TouristoTheme
import com.github.hosseinzafari.touristo.base.ui.RTL
import com.github.hosseinzafari.touristo.presentation.components.*
import com.queezo.app.assets.card_1_1
import com.queezo.app.assets.card_1_2
import com.queezo.app.assets.card_2_1
import com.queezo.app.assets.card_2_2

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 05/05/2023 - 3:12 PM
 * @project Touristo
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen() {
    val tabsIndexState = remember { mutableStateOf(0) }
    val tabsTitle = listOf("جنگل", "دریا", "کوه", "ساحل")

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
                        IconButton(onClick = { }) {
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
                    tabsTitle.forEachIndexed { index, value ->

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
                            onClick = { tabsIndexState.value = index },
                            text = {
                                Text(
                                    text = value,
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
                    item {
                        Spacer(modifier = Modifier.width(16.dp))
                        LocationCard(
                            resId = card_1_1,
                            name = "جنگل ماسوله",
                            location = "مازندران ، ایران",
                            likeCount = 56,
                            onClick = {}
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                    item {
                        Spacer(modifier = Modifier.width(16.dp))
                        LocationCard(
                            resId = card_1_2,
                            name = "جنگل فردوس",
                            location = "گیلان ، ایران",
                            likeCount = 16,
                            onClick = {}
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                    item {
                        Spacer(modifier = Modifier.width(16.dp))
                        LocationCard(
                            resId = card_2_1,
                            name = "دماوند",
                            location = "تهران ، ایران",
                            likeCount = 80,
                            onClick = {}
                        )
                        Spacer(modifier = Modifier.width(16.dp))
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
                        item {
                            BestDestinationCard(resId = card_1_2, province = "گیلان", country  =  "ایران")
                            Spacer(modifier = Modifier.width(16.dp))
                        }

                        item {
                            BestDestinationCard(resId = card_2_2, province = "مازندران", country  =  "ایران")
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
fun ScreenHomePreview() {
    TouristoTheme {
        RTL {
            HomeScreen()
        }
    }
}

