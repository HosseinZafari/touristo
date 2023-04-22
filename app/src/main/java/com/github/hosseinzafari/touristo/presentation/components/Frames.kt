package com.github.hosseinzafari.touristo.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TouristoFrame(
    modifier: Modifier = Modifier.fillMaxSize(),
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    block: @Composable () -> Unit
) = Surface(
    modifier = modifier,
    color = backgroundColor
) {
    block()
}
