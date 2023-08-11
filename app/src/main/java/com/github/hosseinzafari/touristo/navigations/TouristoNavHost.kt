package com.github.hosseinzafari.touristo.navigations

import android.content.Intent
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.hosseinzafari.touristo.presentation.screens.loading.AuthVerificationScreen
import com.github.hosseinzafari.touristo.presentation.screens.login.LoginScreen
import com.github.hosseinzafari.touristo.presentation.screens.SignupScreen
import com.github.hosseinzafari.touristo.presentation.screens.add_location.AddLocationScreen
import com.github.hosseinzafari.touristo.presentation.screens.comment.CommentScreen
import com.github.hosseinzafari.touristo.presentation.screens.bookmark.BookmarkScreen
import com.github.hosseinzafari.touristo.presentation.screens.edit_user_setting.EditUserSettingScreen
import com.github.hosseinzafari.touristo.presentation.screens.home.HomeScreen
import com.github.hosseinzafari.touristo.presentation.screens.location_description.LocationDescScreen
import com.github.hosseinzafari.touristo.presentation.screens.search.SearchScreen
import com.github.hosseinzafari.touristo.presentation.screens.user_setting.UserSettingScreen

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
    startDestination: String = Route.Login.name,
    intent: Intent? = null,
) {

    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ) {

        composable(route = Route.Signup.name + "?msg={msg}", arguments = listOf(navArgument("msg") {
            type = NavType.StringType
            defaultValue = null
            nullable = true
        })) {

            val msg = it.arguments?.getString("msg")

            SignupScreen(
                msg = msg  ,
                onNavigateToLogin = {
                    navController.navigate(Route.Login.name)
                },
                onNavigateToHome = {
                    navController.navigate(Route.Home.name) {
                        popUpTo(navController.graph.id) {
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
                },
                onNavigateToHome = {
                    navController.navigate(Route.Home.name) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Route.UserSetting.name) {
            UserSettingScreen(
                onNavigateToSignup = {
                    navController.navigate(Route.Signup.name)
                },
                onNavigateToBack = {
                    if (navController.previousBackStackEntry != null) {
                        navController.navigateUp()
                    }
                } ,
                onNavigateToBookmark = {
                    navController.navigate(Route.Favorite.name)
                } ,
                onNavigateToEditUserSetting = {
                    navController.navigate(Route.EditUserSetting.name)
                }
            )
        }

        composable(Route.EditUserSetting.name) {
            EditUserSettingScreen(
                onNavigateToBack = {
                    if (navController.previousBackStackEntry != null) {
                        navController.navigateUp()
                    }
                } ,
            )
        }


        composable(Route.AuthVerification.name) {
            Log.i("Test" , "AuthVerification composable")
            AuthVerificationScreen(
                intent = intent,
                onNavigateToSignup = {
                    navController.navigate(Route.Signup.name + "?msg=$it")
                },
                onNavigateToHome = {
                    navController.navigate(Route.Home.name) {
                        popUpTo(navController.graph.id) {
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
                },
                onNavigateToLocationDesc = {
                    navController.navigate(Route.Description.name + "/$it")
                },
                onNavigateToUserSetting = {
                    navController.navigate(Route.UserSetting.name)
                },
                onNavigateToAddLocation = {
                    navController.navigate(Route.AddLocation.name)
                }
            )
        }

        composable(
            route = Route.Search.name + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            val id = it.arguments?.getInt("id")
            SearchScreen(
                destId = id!!,
                onNavigateToHome = {
                    if (navController.previousBackStackEntry != null) {
                        navController.navigateUp()
                    }
                },
                onNavigateToLocationCard = {
                    navController.navigate(Route.Description.name + "/$it")
                },
            )
        }

        composable(
            route = Route.Comment.name + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            val id = it.arguments?.getInt("id")
            CommentScreen(
                locationItemId = id!!
            )
        }

        composable(Route.Favorite.name) {
            BookmarkScreen(
                onNavigateToBack = {
                    if (navController.previousBackStackEntry != null) {
                        navController.navigateUp()
                    }
                },
                onNavigateToLocationDesc = {
                    navController.navigate(Route.Description.name + "/$it")
                }
            )
        }

        composable(Route.AddLocation.name) {
            AddLocationScreen(
                onNavigateToHome = {
                    navController.navigate(Route.Home.name)
                }
            )
        }

        composable(
            route = Route.Description.name + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            }),
        ) {
            val id = it.arguments?.getInt("id")

            LocationDescScreen(
                descId = id!!,
                onNavigateToComment = {
                    navController.navigate(Route.Comment.name + "/$it")
                },
                onNavigateToBack = {
                    if (navController.previousBackStackEntry != null) {
                        navController.navigateUp()
                    }
                }
            )
        }


    }

}
