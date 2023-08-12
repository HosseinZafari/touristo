package com.github.hosseinzafari.touristo.presentation.components

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.queezo.app.assets.card_1_1

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 18/05/2023 - 3:08 PM
 * @project Touristo
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationCard(
    modifier: Modifier = Modifier
        .widthIn(min = 200.dp, max = 280.dp)
        .heightIn(min = 300.dp, max = 400.dp),
    likeCount: Int,
    name: String,
    location: String,
    imageUri: Uri? = null,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(50.dp),
        onClick = onClick,
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            if (imageUri != null) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter  = rememberAsyncImagePainter(imageUri) ,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
                    .background(color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(20.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {

                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = name,
                        color = MaterialTheme.colorScheme.onPrimaryContainer ,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth() ,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = location,
                            color = MaterialTheme.colorScheme.onPrimaryContainer ,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Light
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                imageVector = Icons.Default.Favorite, contentDescription = null,
                                tint =  MaterialTheme.colorScheme.onPrimaryContainer ,
                                modifier = Modifier.size(14.dp) ,
                            )
                            Text(
                                text = likeCount.toString(),
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold ,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }
        }


    }
}