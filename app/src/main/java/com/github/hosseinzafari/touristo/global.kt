package com.github.hosseinzafari.touristo

import android.content.Context
import com.github.hosseinzafari.touristo.core.TouristApplication
import com.github.hosseinzafari.touristo.lang.FaLang
import com.github.hosseinzafari.touristo.lang.TouristoLang
import io.github.jan.supabase.gotrue.user.UserInfo

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 23/04/2023 - 3:32 PM
 * @project Touristo
 */

// easy access to lang resource (default is farsi (persian) language)
var L: TouristoLang = FaLang

// easy cast to TouristApplication
fun App(context: Context) = if (context is TouristApplication) context else null


fun Any?.notNull() = if (this != null) {
    true
} else {
    false
}

fun Any?.isNull() = if (this == null) {
    true
} else {
    false
}