package com.github.hosseinzafari.touristo.core.data.dto

import com.github.hosseinzafari.touristo.core.data.data_model.Bookmark
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
    val id: Int? = null,
    @SerialName("created_at")
    val createdAt: String? = null ,
    @SerialName("user_id")
    val userID: String ,
    @SerialName("location_model")
    val location: LocationModel
)

@Serializable
data class BookmarkModelInsert(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("created_at")
    val createdAt: String? = null ,
    @SerialName("user_id")
    val userID: String ,
    @SerialName("location_id")
    val locationID: Int
)

fun BookmarkModel.toBookmark() = Bookmark(id!! , createdAt , userID , location.toLocation())
fun List<BookmarkModel>.toBookmark() = map { it.toBookmark() }


fun Bookmark.toBookmarkModel() = BookmarkModel(id , createdAt , userID , location.toLocationModel())
fun List<Bookmark>.toBookmarkModel() = map { it.toBookmarkModel() }


