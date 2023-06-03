package com.github.hosseinzafari.touristo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.github.hosseinzafari.touristo.base.system.XComponentActivity
import com.github.hosseinzafari.touristo.base.theme.TouristoTheme
import com.github.hosseinzafari.touristo.base.ui.RTL
import com.github.hosseinzafari.touristo.core.data.local.usecases.IsUserLoginedUseCase
import com.github.hosseinzafari.touristo.navigations.Route
import com.github.hosseinzafari.touristo.navigations.TouristoNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity @Inject constructor() : XComponentActivity() {

    @Inject
    lateinit var logic: MainActivityLogic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window , false)

        lifecycleScope.launch(Dispatchers.IO) {
            val destination = if (logic.isUserLogined()) Route.Home else Route.Login

            withContext(Dispatchers.Main) {
                setContent {
                    TouristoPresentation(destination = destination)
                }
            }
        }
    }
}

@Composable
fun TouristoPresentation(destination: Route) {
    TouristoTheme {
        ChangeSystemBarsColor()
        RTL {
            TouristoNavHost(startDestination = destination)
        }
    }
}