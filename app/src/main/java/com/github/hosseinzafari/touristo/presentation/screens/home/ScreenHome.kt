package com.github.hosseinzafari.touristo.presentation.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.hosseinzafari.touristo.base.theme.TouristoTheme
import com.github.hosseinzafari.touristo.base.ui.RTL
import com.github.hosseinzafari.touristo.presentation.components.TouristoFrame

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 05/05/2023 - 3:12 PM
 * @project Touristo
 */

@Composable
fun HomeScreen(){
    TouristoFrame {
        Text("Home Screen")
    }
}

@Preview()
@Composable
fun ScreenHomePreview(){
    TouristoTheme {
        RTL {
            HomeScreen()
        }
    }
}

