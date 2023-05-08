package com.github.hosseinzafari.touristo

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 24/04/2023 - 5:54 PM
 * @project Touristo
 */

@Composable
fun ChangeSystemBarsColor(
    color: Color = Color.Transparent ,
    useDarkIcons: Boolean = !isSystemInDarkTheme() ,
) {
    val systemUiController = rememberSystemUiController()

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = color,
            darkIcons = useDarkIcons
        )

        onDispose {}
    }
}


// easy cast to TouristApplication
fun App(context: Context) = if (context is TouristApplication) context else null
