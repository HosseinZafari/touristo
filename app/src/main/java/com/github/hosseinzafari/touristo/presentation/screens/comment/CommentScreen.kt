package com.github.hosseinzafari.touristo.presentation.screens.comment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.hosseinzafari.touristo.base.theme.TouristoTheme
import com.github.hosseinzafari.touristo.base.ui.RTL
import com.github.hosseinzafari.touristo.presentation.components.TitleBold
import com.github.hosseinzafari.touristo.presentation.components.TouristoFrame
import com.queezo.app.assets.avatar

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 06/06/2023 - 7:03 PM
 * @project Touristo
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CommentScreen(
    viewModel: CommentViewModel = hiltViewModel(),
    locationItemId: Int? ,
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
                modifier = Modifier.fillMaxSize()
            ) {

                TitleBold(text = "نظرات")
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(modifier = Modifier.weight(9f)) {

                    item(1) {
                        Column (
                            modifier = Modifier
                                .padding(8.dp)
                                .background(
                                    color = Color.LightGray.copy(alpha = 0.5f),
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(8.dp) ,
                                verticalAlignment = Alignment.CenterVertically ,
                            ) {
                                Image(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(50.dp)
                                        .clip(CircleShape) ,
                                    painter = painterResource(id = avatar),
                                    contentDescription = "avatar image",
                                    contentScale = ContentScale.Crop ,
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(text = "حسین ظفری" , style = MaterialTheme.typography.titleMedium)
                            }

                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                modifier = Modifier.padding(8.dp) ,
                                text = "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است" ,
                                style = MaterialTheme.typography.bodySmall ,
                                textAlign = TextAlign.Justify,
                                lineHeight = 24.sp ,
                                letterSpacing = 2.sp ,
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                    }

                    item(2) {
                        Column (
                            modifier = Modifier
                                .padding(8.dp)
                                .background(
                                    color = Color.LightGray.copy(alpha = 0.5f),
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(8.dp) ,
                                verticalAlignment = Alignment.CenterVertically ,
                            ) {
                                Image(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(50.dp)
                                        .clip(CircleShape) ,
                                    painter = painterResource(id = avatar),
                                    contentDescription = "avatar image" ,
                                    contentScale = ContentScale.Crop ,
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(text = "حسین ظفری" , style = MaterialTheme.typography.titleMedium)
                            }

                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                modifier = Modifier.padding(8.dp) ,
                                text = "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است" ,
                                style = MaterialTheme.typography.bodySmall ,
                                textAlign = TextAlign.Justify,
                                lineHeight = 24.sp ,
                                letterSpacing = 2.sp ,
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                    }


                }

                Row (
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth() ,
                    horizontalArrangement = Arrangement.SpaceBetween ,
                    verticalAlignment = Alignment.CenterVertically ,
                ) {

                    TextField(
                      modifier = Modifier.weight(8f) ,
                      value = state.value.comment,
                      onValueChange = {
                          processor.sendAction(CommentAction.OnChangeComment(it))
                      }
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        modifier = Modifier.weight(2f) ,
                        onClick = {
                        processor.sendAction(CommentAction.Submit)
                    }) {
                        Icon(modifier = Modifier.rotate(180f) ,imageVector = Icons.Default.Send , contentDescription = "send")
                    }

                }
            }

        }

    }

}


@Preview
@Composable
fun CommentScreenPreview() {
    TouristoTheme {
        RTL {
            CommentScreen(locationItemId = null)
        }
    }
}
