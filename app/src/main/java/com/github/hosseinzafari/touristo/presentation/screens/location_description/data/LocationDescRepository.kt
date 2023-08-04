package com.github.hosseinzafari.touristo.presentation.screens.location_description.data

import com.github.hosseinzafari.touristo.core.data.data_model.Bookmark
import com.github.hosseinzafari.touristo.core.data.data_model.Like
import com.github.hosseinzafari.touristo.core.data.data_model.Location
import com.github.hosseinzafari.touristo.core.data.dto.LikeModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 02/08/2023 - 1:21 PM
 * @project Touristo
 */

class LocationDescRepository @Inject constructor(
    val dataSource: LocationDescDataSource ,
) : LocationDescDomain {
    override suspend fun getLocation(id: Int) = dataSource.getLocation(id)
    override suspend fun bookmarkIsExists(locationID: Int) =  dataSource.bookmarkIsExists(locationID)

    override suspend fun addBookmark(locationID: Int) = dataSource.addBookmark(locationID)
    override suspend fun removeBookmark(locationID: Int) = dataSource.removeBookmark(locationID)

    override suspend fun likeIsExists(locationID: Int) = dataSource.likeIsExists(locationID)

    override suspend fun addLike(locationID: Int) = dataSource.addLike(locationID)
    override suspend fun removeLike(locationID: Int) = dataSource.removeLike(locationID)


}