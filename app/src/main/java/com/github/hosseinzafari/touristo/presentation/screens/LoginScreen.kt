package com.github.hosseinzafari.touristo.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.hosseinzafari.touristo.base.theme.TouristoTheme
import com.github.hosseinzafari.touristo.presentation.components.TouristoFrame

@Composable
fun LoginScreen() {

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
   TouristoTheme {
       TouristoFrame {
           LoginScreen()
       }
   }
}

