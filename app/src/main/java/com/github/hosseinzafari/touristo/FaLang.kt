package com.github.hosseinzafari.touristo

import com.github.hosseinzafari.touristo.base.system.XLang

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 23/04/2023 - 3:00 PM
 * @project Touristo
 */

object FaLang : TouristoLang  {
    override val app_name: String
        get() = "توریستو"
    override val login_title: String
        get() = "ورود به حساب کاربری"
    override val login_header_pin: String
        get() = "کویر مرکزی ، ایران"
    override val login_email_textfield: String
        get() = "ایمیل خود را وارد کنید"
    override val login_password_textfield: String
        get() = "رمز عبور خود را وارد کنید"
    override val login_enter_button: String
        get() = "ورود"
    override val login_is_new_user: String
        get() = "حساب کاربری ندارید ؟ "
    override val login_register_here: String
        get() = "ثبت نام"
}