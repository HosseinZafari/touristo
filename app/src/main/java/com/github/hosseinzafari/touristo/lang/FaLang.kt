package com.github.hosseinzafari.touristo.lang

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
    override val login_wrong_email_or_password: String
        get() = "رمز عبور یا ایمیل مورد نظر شما صحیح نیست!"
    override val login_empty_email_or_password: String
        get() = "لطفا ایمیل یا رمز عبور را خالی وارد نکنید."
    override val login_submit_error: String
        get() = "مشکلی در ورود به حساب شما به وجود آمده"
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
    override val signup_empty_edit_texts: String
        get() = "لطفا هیچ فیلدی را خالی نگذارید"
    override val signup_password_not_equals: String
        get() = "رمزعبور با تکرار رمزعبور یکسان نیست"
    override val signup_password_less_length: String
        get() = "حداقل تعداد کارکتر های رمزعبور 5 است"
    override val signup_user_is_exists: String
        get() = "حسابی با این ایمیل قبلا ساخته شده است"
    override val signup_submit_error: String
        get() = "مشکلی در ثبت نام شما به وجود آمده"
    override val signup_submit_checking_email: String
        get() = "لطفا لینک تاییدی در ایمیل خود را کلیک کنید"
}