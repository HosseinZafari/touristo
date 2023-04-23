package com.github.hosseinzafari.touristo

import com.github.hosseinzafari.touristo.base.system.XLang

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 23/04/2023 - 2:55 PM
 * @project Touristo
 */

interface TouristoLang : XLang {
    val app_name: String

    val login_title: String
    val login_header_pin: String
    val login_email_textfield: String
    val login_password_textfield: String
    val login_enter_button: String
    val login_is_new_user: String
    val login_register_here: String

}