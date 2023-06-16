package com.github.hosseinzafari.touristo.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.queezo.app.assets.card_2_2

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 18/05/2023 - 6:15 PM
 * @project Touristo
 */
 
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BestDestinationCard(
    modifier : Modifier = Modifier.height(100.dp),
    @DrawableRes resId: Int ,
    province: String ,
    country: String ,
    onClick: () -> Unit ,
) {
    Card(
        modifier = modifier,
        onClick =  onClick,
        colors = CardDefaults.cardColors(containerColor = Color(0xffefefef)) ,
        shape = RoundedCornerShape(30.dp) ,
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Image(
                modifier = Modifier.width(100.dp).fillMaxHeight() ,
                painter = painterResource(resId),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Column (
                modifier= Modifier.padding(horizontal = 16.dp , vertical = 8.dp) ,
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally ,
            ) {
                Text(text = province , fontWeight = FontWeight.Bold , style = MaterialTheme.typography.titleMedium)
                Text(text = country, fontWeight = FontWeight.Light , style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}