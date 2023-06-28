package com.github.hosseinzafari.touristo.presentation.components

import android.util.Log
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 25/06/2023 - 12:26 PM
 * @project Touristo
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TouristoSpinner(
    modifier: Modifier = Modifier,
    list: Map<Int, String>,
    selectedOptionText: Pair<Int, String> ,
    onSelectedOptionChange: (Pair<Int,String>) -> Unit ,
) {


    var expanded by remember {
        mutableStateOf(false)
    }


    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
            Log.i("Test" , "onExpandedChange  $it")
        }) {
        TextField(
            readOnly = true,
            value = selectedOptionText.second ,
            onValueChange = { },
            label = { Text("مقصد") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            list.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onSelectedOptionChange(selectionOption.toPair())
                    },
                    text = {
                        Text(text = selectionOption.value)
                    }
                )
            }
        }
    }

}