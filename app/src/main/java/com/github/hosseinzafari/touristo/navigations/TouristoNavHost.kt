package com.github.hosseinzafari.touristo.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.hosseinzafari.touristo.presentation.screens.login.LoginScreen
import com.github.hosseinzafari.touristo.presentation.screens.SignupScreen
import com.github.hosseinzafari.touristo.presentation.screens.comment.CommentScreen
import com.github.hosseinzafari.touristo.presentation.screens.favorite.FavoriteScreen
import com.github.hosseinzafari.touristo.presentation.screens.home.HomeScreen
import com.github.hosseinzafari.touristo.presentation.screens.location_description.LocationDescScreen
import com.github.hosseinzafari.touristo.presentation.screens.search.SearchScreen

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
            HomeScreen(
                onNavigateToSearch = {
                     navController.navigate(Route.Search.name + "/$it")
                } ,
                onNavigateToLocationDesc = {
                    navController.navigate(Route.Description.name + "/$it")
                } ,
            )
        }

        composable(
            route = Route.Search.name + "/{id}" ,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            val id = it.arguments?.getInt("id")
            SearchScreen(
                destId = id!! ,
                onNavigateToHome = {
                   navController.navigate(Route.Home.name)
                } ,
                onNavigateToLocationCard = {
                   navController.navigate(Route.Description.name + "/$it")
                } ,
            )
        }

        composable(
            route = Route.Comment.name + "/{id}" ,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            val id = it.arguments?.getInt("id")
            CommentScreen(
                locationItemId = id
            )
        }

        composable(Route.Favorite.name) {
            FavoriteScreen()
        }

        composable(
            route = Route.Description.name + "/{id}" ,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            }) ,
        ) {
            val id = it.arguments?.getInt("id")

            LocationDescScreen(
                descId = id!! ,
                onNavigateToComment = {
                    navController.navigate(Route.Comment.name + "/$it")
                } ,
                onNavigateToBack = {
                    navController.popBackStack()
                }
            )
        }


    }

}
