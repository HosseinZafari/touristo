package com.github.hosseinzafari.touristo.core.data.data_model

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 15/06/2023 - 6:55 PM
 * @project Touristo
 */
 
data class BookmarkModel(
    val id: Int ,
    val userID: Int ,
    val locationID: Int
)

val BookmarkData : MutableList<BookmarkModel> = mutableListOf()