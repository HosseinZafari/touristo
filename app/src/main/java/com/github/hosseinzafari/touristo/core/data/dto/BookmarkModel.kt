package com.github.hosseinzafari.touristo.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 15/06/2023 - 6:55 PM
 * @project Touristo
 */
@Serializable
data class BookmarkModel(
    @SerialName("id")
    val id: Int ,
    @SerialName("user_id")
    val userID: String ,
    @SerialName("location_id")
    val locationID: Int
)

val BookmarkData : MutableList<BookmarkModel> = mutableListOf()