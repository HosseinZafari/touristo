package com.github.hosseinzafari.touristo.core

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity @Inject constructor(
) : XComponentActivity() {

    @Inject
    lateinit var client: SupabaseClient

    @Inject
    lateinit var logic: MainActivityLogic

    val main by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        splash.setKeepOnScreenCondition {
            main.splashScreenVisible
        }

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        var destination: String = Route.Login.name

        if (isAuthVerification(intent)) { // deeplink checking
            destination = Route.AuthVerification.name
            main.splashScreenVisible = false
        } else {
            lifecycleScope.launch(Dispatchers.IO) {
                val loggedIn = logic.isUserLoggedIn()
                Log.i("Test", "loggedIn $loggedIn")
                runOnUiThread {
                    main.navController?.navigate(if (loggedIn) Route.Home.name else Route.Signup.name)
                    main.splashScreenVisible = false
                }
            }
        }

        setContent {
            val navController = rememberNavController()
            main.navController = navController

            TouristoPresentation(
                intent = intent,
                navHostController = navController,
                destination = destination,
            )
        }
    }

    private fun isAuthVerification(intent: Intent?) =
        intent != null && intent.data != null && intent.data?.host == "touristo.auth" && intent.data?.scheme == "app"

    suspend fun submitAuth(intent: Intent) {


//            client.handleDeeplinks(intent) {
//                Log.i("Test", "handleDeeplinks successfully " + it)
//                runOnUiThread {
//                    main.navController?.navigate(Route.Home.name)
//                }
//            }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.i("Test", "onNewIntent() called ")


    }

}

@Composable
fun TouristoPresentation(
    intent: Intent?,
    navHostController: NavHostController,
    destination: String
) {
    TouristoTheme {
        ChangeSystemBarsColor()
        RTL {
            TouristoNavHost(
                intent = intent,
                navController = navHostController,
                startDestination = destination
            )
        }
    }
}