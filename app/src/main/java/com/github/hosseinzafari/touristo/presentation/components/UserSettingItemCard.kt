package com.github.hosseinzafari.touristo.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 07/08/2023 - 1:28 PM
 * @project Touristo
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable()
fun UserSettingItemCard(
    modifier: Modifier = Modifier.fillMaxWidth() ,
    imageVector: ImageVector ,
    itemName: String ,
    onClick: () -> Unit ,
) {
    Card (
        modifier = modifier ,
        onClick = onClick ,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp) ,
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.SpaceBetween ,
        ) {
            Row (
                modifier = Modifier.weight(3f) ,
                verticalAlignment = Alignment.CenterVertically ,
            ) {
                Icon(
                    imageVector = imageVector ,
                    modifier = Modifier.size(18.dp) ,
                    contentDescription = "bookmark item"
                )
                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = itemName ,
                    style = MaterialTheme.typography.titleMedium ,
                )
            }


            Row (
                modifier = Modifier.weight(7f , false) ,
            ){
                Icon(Icons.Default.ChevronLeft , contentDescription = "$itemName item")
            }
        }
    }

}