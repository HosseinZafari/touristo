package com.github.hosseinzafari.touristo.presentation.screens.search

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.hosseinzafari.touristo.base.theme.MilkColor
import com.github.hosseinzafari.touristo.base.theme.TouristoTheme
import com.github.hosseinzafari.touristo.base.ui.RTL
import com.github.hosseinzafari.touristo.presentation.components.LocationCard
import com.github.hosseinzafari.touristo.presentation.components.TitleBold
import com.github.hosseinzafari.touristo.presentation.components.TouristoFrame
import com.github.hosseinzafari.touristo.presentation.screens.home.HomeScreen
import com.queezo.app.assets.card_1_1
import com.queezo.app.assets.card_1_2
import com.queezo.app.assets.card_2_1

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 02/06/2023 - 2:38 PM
 * @project Touristo
 */

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {

    val processor = viewModel.processor
    val state = processor.subscriberState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }


    processor.SubscribeEffect(
        state = state.value,
        statusBlock = {

        } ,
        effectsBlock = {

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
                .padding(it)
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

                TitleBold(text = "جستجو")
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp) ,
                    horizontalArrangement = Arrangement.Center ,
                    verticalAlignment = Alignment.CenterVertically ,
                ) {

                    TextField(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp) ,
                        shape = RoundedCornerShape(16.dp) ,
                        value = state.value.text,
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent ,
                            unfocusedIndicatorColor = Color.Transparent ,
                            containerColor = Color(0xffefefef) ,
                            textColor = Color.DarkGray ,
                            placeholderColor = Color.Gray ,
                        ) ,
                        onValueChange = { processor.sendAction(SearchAction.OnSearchChanged(it)) } ,
                        placeholder = {
                            Text(text = "مقصد خود را جستجو کنید")
                        } ,
                        trailingIcon = {
                            IconButton(onClick = { processor.sendAction(SearchAction.Submit) }) {
                                Icon (
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "search submit"
                                )
                            }
                        },
                        singleLine = true ,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text) ,
                    )

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
                            onClick = {} ,
                            modifier = Modifier
                                .widthIn(min = 150.dp, max = 250.dp)
                                .heightIn(min = 250.dp, max = 550.dp)
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
                            onClick = {} ,
                            modifier = Modifier
                                .widthIn(min = 150.dp, max = 250.dp)
                                .heightIn(min = 250.dp, max = 550.dp)
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
                            onClick = {} ,
                            modifier = Modifier
                                .widthIn(min = 150.dp, max = 250.dp)
                                .heightIn(min = 250.dp, max = 550.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }
        }
    }

}

@Preview()
@Composable
fun SearchScreenPreview() {
    TouristoTheme {
        RTL {
            SearchScreen()
        }
    }
}