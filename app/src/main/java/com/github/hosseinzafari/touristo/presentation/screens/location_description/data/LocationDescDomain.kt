package com.github.hosseinzafari.touristo.presentation.screens.location_description.data

import com.github.hosseinzafari.touristo.base.system.data_layer.XDomain
import com.github.hosseinzafari.touristo.core.data.data_model.Bookmark
import com.github.hosseinzafari.touristo.core.data.data_model.Like
import com.github.hosseinzafari.touristo.core.data.data_model.Location
import kotlinx.coroutines.flow.Flow

/**
 * @author hosse
 * @created 02/08/2023 - 1:15 PM
 * @project Touristo
 */

interface LocationDescDomain : XDomain {

    suspend fun getLocation(id: Int): Flow<Location>


    suspend fun bookmarkIsExists(locationID: Int): Flow<Boolean>
    suspend fun addBookmark(locationID: Int)
    suspend fun removeBookmark(locationID: Int)


    suspend fun likeIsExists(locationID: Int): Flow<Boolean>
    suspend fun addLike(locationID: Int)
    suspend fun removeLike(locationID: Int)

    suspend fun getLikeCount(locationID: Int): Flow<Int>

}