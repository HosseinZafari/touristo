package com.github.hosseinzafari.touristo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.github.hosseinzafari.touristo.base.system.XComponentActivity
import com.github.hosseinzafari.touristo.base.theme.TouristoTheme
import com.github.hosseinzafari.touristo.base.ui.RTL
import com.github.hosseinzafari.touristo.navigations.TouristoNavHost
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
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
fun TouristoPresentation() {
    TouristoTheme {
        ChangeSystemBarsColor()
        RTL {
            TouristoNavHost()
        }
    }
}