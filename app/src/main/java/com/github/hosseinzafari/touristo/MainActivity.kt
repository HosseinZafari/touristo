package com.github.hosseinzafari.touristo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.hosseinzafari.touristo.base.system.XComponentActivity
import com.github.hosseinzafari.touristo.base.theme.TouristoTheme

class MainActivity : XComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TouristoPresentation()
        }
    }
}

@Composable
fun TouristoPresentation() {
    TouristoTheme {

    }
}