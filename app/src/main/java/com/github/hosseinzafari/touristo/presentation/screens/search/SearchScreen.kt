package com.github.hosseinzafari.touristo.presentation.screens.search

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
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
import com.google.accompanist.placeholder.material3.placeholder
import com.queezo.app.assets.card_1_1

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 02/06/2023 - 2:38 PM
 * @project Touristo
 */

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel() ,
    onNavigateToHome: () -> Unit ,
    onNavigateToLocationCard : (Int) -> Unit ,
    destId: Int  ,
) {

    val processor = viewModel.processor
    val state = processor.subscriberState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 =  0) {
        if (destId != 0) {
            processor.sendAction(SearchAction.GetSelectedProvince(destId))
        }
    }

    LaunchedEffect(key1 =  state.value.selectedProvince) {
        val province = state.value.selectedProvince
        Log.i("Test" , "selectedProvince $province")
        if (destId != 0 && province != null) {
            processor.sendAction(SearchAction.OnSearchChanged(province.name))
            processor.sendAction(SearchAction.Submit)
        }
    }


    processor.SubscribeEffect(
        state = state.value,
        statusBlock = {
        } ,
        effectsBlock = {
            when(it) {
                is SearchEffect.NavigateToHome -> {
                    onNavigateToHome()
                }

                is SearchEffect.NavigateToLocationCardItem -> {
                    onNavigateToLocationCard(it.locationID)
                }

            }
            processor.setState(state.value.copy(effects = null))

        },
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

                TitleBold(modifier = Modifier.padding(vertical = 16.dp) , text = "جستجو")

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp) ,
                    horizontalArrangement = Arrangement.Center ,
                    verticalAlignment = Alignment.CenterVertically ,
                ) {

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp) ,
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
                    if(state.value.status == XStatus.Loading) {
                        repeat(3) {
                            item {
                                LocationCard(
                                    resId = card_1_1,
                                    name = "",
                                    location = "",
                                    likeCount = 0,
                                    onClick = {} ,
                                    modifier = Modifier
                                        .widthIn(min = 150.dp, max = 250.dp)
                                        .heightIn(min = 250.dp, max = 550.dp)
                                        .placeholder(visible = true , shape = RoundedCornerShape(50.dp) )
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                        }
                    } else {
                        if(state.value.data.size > 0) {
                            items(state.value.data) {
                                Spacer(modifier = Modifier.width(16.dp))
                                LocationCard(
                                    resId = it.resID,
                                    name = it.name,
                                    location = it.location.name + ", ایران",
                                    likeCount = it.likeCount,
                                    imageUri = it.imageUri,
                                    onClick = {
                                      processor.sendAction(SearchAction.ClickOnLocationCard(it.id))
                                    } ,
                                    modifier = Modifier
                                        .widthIn(min = 150.dp, max = 250.dp)
                                        .heightIn(min = 250.dp, max = 550.dp)
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                        } else {
                            item {
                                Column {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        text = "هیچ رکوردی مرتبط با این جستجو پیدا نشد" ,
                                        style = MaterialTheme.typography.titleSmall
                                    )
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
fun SearchScreenPreview() {
    TouristoTheme {
        RTL {
            SearchScreen( onNavigateToHome = {} , onNavigateToLocationCard = {} , destId = 0)
        }
    }
}