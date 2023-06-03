package com.github.hosseinzafari.touristo.core.lang

import com.github.hosseinzafari.touristo.base.system.XLang

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 23/04/2023 - 2:55 PM
 * @project Touristo
 */

interface TouristoLang : XLang {
    val app_name: String

    // login screen
    val login_title: String
    val login_header_pin: String
    val login_email_textfield: String
    val login_password_textfield: String
    val login_enter_button: String
    val login_is_new_user: String
    val login_register_here: String
    val login_wrong_email_or_password: String
    val login_empty_email_or_password: String
    val login_submit_error: String

    // signup screen
    val signup_title: String
    val signup_header_pin: String
    val signup_email_textfield: String
    val signup_name_textfield: String
    val signup_replay_password_textfield: String
    val signup_password_textfield: String
    val signup_enter_button: String
    val signup_is_old_user: String
    val signup_login_here: String
    val signup_empty_edit_texts: String
    val signup_password_not_equals: String
    val signup_password_less_length: String
    val signup_user_is_exists: String
    val signup_submit_error: String

}