package com.github.hosseinzafari.touristo.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 18/05/2023 - 2:38 PM
 * @project Touristo
 */

@Composable
fun TitleExtraBold(
    modifier: Modifier = Modifier.padding(end = 4.dp),
    text: String
) {
    Text(modifier = modifier ,
        text = text ,
        color = MaterialTheme.colorScheme.primary ,
        style = MaterialTheme.typography.displaySmall ,
        fontWeight = FontWeight.ExtraBold
    )
}

@Composable
fun TitleBold(
    modifier: Modifier = Modifier.padding(end = 4.dp),
    text: String
) {
    Text(
        modifier = modifier ,
        text = text ,
        color = MaterialTheme.colorScheme.primary ,
        style = MaterialTheme.typography.titleLarge ,
        fontWeight = FontWeight.Bold
    )
}