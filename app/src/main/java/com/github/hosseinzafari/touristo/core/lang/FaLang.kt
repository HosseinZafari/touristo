package com.github.hosseinzafari.touristo.core.lang

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 23/04/2023 - 3:00 PM
 * @project Touristo
 */

object FaLang : TouristoLang {
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
    override val signup_title: String
        get() = "ایجاد حساب کاربری"
    override val signup_header_pin: String
        get() = "تخت جمشید، شیراز ایران"
    override val signup_email_textfield: String
        get() =  "ایمیل خود را وارد کنید"
    override val signup_name_textfield: String
        get() = "نام خود را وارد کنید"
    override val signup_replay_password_textfield: String
        get() = "تکرار رمز عبور"
    override val signup_password_textfield: String
        get() = "رمز عبور خود را وارد کنید"
    override val signup_enter_button: String
        get() =  "ثبت نام"
    override val signup_is_old_user: String
        get() = "حساب کاربری دارید ؟ "
    override val signup_login_here: String
        get() = "وارد شوید"
}