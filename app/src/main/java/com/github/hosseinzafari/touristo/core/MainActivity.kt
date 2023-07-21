package com.github.hosseinzafari.touristo.core

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.hosseinzafari.touristo.ChangeSystemBarsColor
import com.github.hosseinzafari.touristo.base.system.XComponentActivity
import com.github.hosseinzafari.touristo.base.theme.TouristoTheme
import com.github.hosseinzafari.touristo.base.ui.RTL
import com.github.hosseinzafari.touristo.navigations.Route
import com.github.hosseinzafari.touristo.navigations.TouristoNavHost
import dagger.hilt.android.AndroidEntryPoint
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.handleDeeplinks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity @Inject constructor(
) : XComponentActivity() {

    @Inject
    lateinit var client: SupabaseClient

    @Inject
    lateinit var logic: MainActivityLogic

    val main: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        var destination: Route = Route.Signup

        if (intent != null && intent.data != null) {
            val action = intent.action
            val data = intent.data
            Log.i("Test", "onNewIntent() intent action " + action)
            Log.i("Test", "onNewIntent() intent data " + data)
            client.handleDeeplinks(intent) {
                Log.i("Test", "handleDeeplinks successfully " + it)
                runOnUiThread {
                    main.navController?.navigate(Route.Home.name)
                }
            }
        } else {
            lifecycleScope.launch(Dispatchers.IO) {
                val loggedIn = logic.isUserLoggedIn()
                Log.i("Test", "loggedIn $loggedIn")
                destination = if (loggedIn) Route.Home else Route.Signup
            }
        }

        setContent {
            val navController = rememberNavController()
            main.navController = navController

            TouristoPresentation(
                navHostController = navController,
                destination = destination
            )
        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.i("Test", "onNewIntent() called ")
       /* val main: MainViewModel by viewModels()

        if (intent != null && intent.data != null) {
            val action = intent.action
            val data = intent.data
            Log.i("Test", "onNewIntent() intent action " + action)
            Log.i("Test", "onNewIntent() intent data " + data)
            client.handleDeeplinks(intent) {
                Log.i("Test", "handleDeeplinks successfully " + it)
                main.navController?.navigate(Route.Home.name)
            }
        }*/

    }

    override fun onResume() {
        super.onResume()

    }
}

@Composable
fun TouristoPresentation(navHostController: NavHostController, destination: Route) {
    TouristoTheme {
        ChangeSystemBarsColor()
        RTL {
            TouristoNavHost(navController = navHostController, startDestination = destination)
        }
    }
}