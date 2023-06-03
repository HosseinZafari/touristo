package com.github.hosseinzafari.touristo.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.hosseinzafari.touristo.presentation.screens.login.LoginScreen
import com.github.hosseinzafari.touristo.presentation.screens.SignupScreen
import com.github.hosseinzafari.touristo.presentation.screens.home.HomeScreen

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 24/04/2023 - 5:20 PM
 * @project Touristo
 */

@Composable
fun TouristoNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination : Route = Route.Login,
) {

    NavHost(navController = navController, modifier = modifier , startDestination = startDestination.name) {
        composable(Route.Signup.name) {
            SignupScreen(
                onNavigateToLogin = {
                    navController.navigate(Route.Login.name)
                } ,
                onNavigateToHome = {
                    navController.navigate(Route.Home.name) {
                        popUpTo(navController.graph.id){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Route.Login.name) {
            LoginScreen(
                onNavigateToSignup = {
                    navController.navigate(Route.Signup.name)
                } ,
                onNavigateToHome = {
                    navController.navigate(Route.Home.name) {
                        popUpTo(navController.graph.id){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Route.Home.name) {
            HomeScreen( )
        }
    }

}
