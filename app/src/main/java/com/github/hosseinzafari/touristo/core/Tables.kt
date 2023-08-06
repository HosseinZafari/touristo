package com.github.hosseinzafari.touristo.core

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 20/07/2023 - 9:34 PM
 * @project Touristo
 */

enum class Tables(val text: String) {
    User("users") ,
    Location("location_model") ,
    BestDestination("best_destination_model") ,
    Like("like_model") ,
    Comment("comment_model") ,
    Category("category_model") ,
    Province("province_model") ,;


    override fun toString(): String {
        return text
    }

}