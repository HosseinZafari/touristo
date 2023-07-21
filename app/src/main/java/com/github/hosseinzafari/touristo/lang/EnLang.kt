package com.github.hosseinzafari.touristo.lang

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 23/04/2023 - 3:00 PM
 * @project Touristo
 */

object EnLang : TouristoLang {
    override val app_name: String
        get() = "Touristo"
    override val login_title: String
        get() = "Login"
    override val login_header_pin: String
        get() = "Central desert , Iran"
    override val login_email_textfield: String
        get() = "Enter your email"
    override val login_password_textfield: String
        get() = "Enter your password"
    override val login_enter_button: String
        get() = "Login"
    override val login_is_new_user: String
        get() = "You Haven't any Account ? "
    override val login_register_here: String
        get() = "Register Here"
    override val login_wrong_email_or_password: String
        get() = "Your email or password is wrong"
    override val login_empty_email_or_password: String
        get() = "Please fill all of the fields"
    override val login_submit_error: String
        get() = "We have some issue in login"
    override val signup_title: String
        get() = "Signup"
    override val signup_header_pin: String
        get() = "Takhte Jamshid , Iran Shiraz"
    override val signup_email_textfield: String
        get() = "Enter your email"
    override val signup_name_textfield: String
        get() = "Enter your name"
    override val signup_replay_password_textfield: String
        get() = "Replay password"
    override val signup_password_textfield: String
        get() = "Enter your password"
    override val signup_enter_button: String
        get() = "Create Account"
    override val signup_is_old_user: String
        get() = "You have already an account ? "
    override val signup_login_here: String
        get() = "Login Here"
    override val signup_empty_edit_texts: String
        get() = "Fill all of the fields"
    override val signup_password_not_equals: String
        get() = "Password and replay not equal"
    override val signup_user_is_exists: String
        get() = "Already created account with this email"
    override val signup_submit_error: String
        get() = "We have some issue in signup"
    override val signup_password_less_length: String
        get() = "Minimum password character is 5"
}