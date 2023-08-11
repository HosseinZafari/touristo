package com.github.hosseinzafari.touristo.navigations

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 24/04/2023 - 5:27 PM
 * @project Touristo
 */

sealed class Route (
    val name : String ,
){
    object Login : Route (name = "login")
    object Signup : Route (name = "signup")
    object Home : Route (name = "home")
    object Description : Route (name = "desc")
    object Favorite : Route (name = "favorite")
    object Search : Route (name = "search")
    object Comment : Route (name = "comment")
    object AddLocation : Route (name = "add_location")
    object AuthVerification : Route (name = "auth_verify")

    object UserSetting : Route (name = "user_setting")
    object EditUserSetting : Route (name = "edit_user_setting")

}