package com.github.hosseinzafari.touristo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.github.hosseinzafari.touristo.base.system.XComponentActivity
import com.github.hosseinzafari.touristo.base.theme.TouristoTheme
import com.github.hosseinzafari.touristo.base.ui.RTL
import com.github.hosseinzafari.touristo.presentation.screens.LoginScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : XComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window , false)
        setContent {
            TouristoPresentation()
        }
    }
}
@Composable
fun TransparentSystemBars() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )

        onDispose {}
    }
}

@Composable
fun TouristoPresentation() {
    TouristoTheme {
        TransparentSystemBars()
        RTL {
            LoginScreen()
        }
    }
}