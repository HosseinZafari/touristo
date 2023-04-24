package com.github.hosseinzafari.touristo.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.github.hosseinzafari.touristo.L
import com.github.hosseinzafari.touristo.base.theme.MilkColor
import com.github.hosseinzafari.touristo.base.ui.innerShadow
import com.queezo.app.assets.login_header
import kotlinx.coroutines.delay

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 24/04/2023 - 12:40 PM
 * @project Touristo
 */
 
@Composable
fun ColumnScope.AuthHeader (
    @DrawableRes  drawableRes: Int ,
    locationName : String ,
    colorShadow: Color  ,
    top: Dp = 0.dp ,
    start: Dp = 0.dp,
    end: Dp = 0.dp ,
    bottom: Dp = 0.dp ,
    heightBox: Dp = 200.dp ,
    blurShadow: Dp = 35.dp ,
    offsetVertical: Dp = (-55).dp ,
    delayAnimation: Long = 2000,
) {
    val stateHintLocationHeaderAnimate = remember {
        MutableTransitionState(false)
    }

    LaunchedEffect(key1 = 1) {
        delay(delayAnimation)
        stateHintLocationHeaderAnimate.targetState = true
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(heightBox)
            .innerShadow(
                color = colorShadow ,
                offsetY = offsetVertical,
                blur = blurShadow
            )
    ) {

        Image(
            painter = painterResource(drawableRes),
            contentDescription = "Auth Header",
            contentScale = ContentScale.Crop,
            alpha = 0.8f,
            modifier = Modifier.fillMaxWidth() ,
        )

        this@AuthHeader.AnimatedVisibility(
            visibleState = stateHintLocationHeaderAnimate,
            enter = expandVertically(
                expandFrom = Alignment.Top
            ) + expandHorizontally(expandFrom = Alignment.Start),
            exit = shrinkVertically(
                shrinkTowards = Alignment.Top
            ) + shrinkHorizontally (
                shrinkTowards = Alignment.Start
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = top, start = start) ,
            ) {
                Icon(
                    Icons.Outlined.LocationOn,
                    modifier = Modifier.size(20.dp),
                    contentDescription = "location on",
                    tint = Color.White
                )
                Text(
                    text = locationName,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(2.dp),
                    color = Color.White,
                )
            }
        }
    }
}