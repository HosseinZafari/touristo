package com.github.hosseinzafari.touristo.presentation.screens.bookmark.data

import com.github.hosseinzafari.touristo.base.system.data_layer.XDomain
import com.github.hosseinzafari.touristo.core.data.data_model.Bookmark
import kotlinx.coroutines.flow.Flow

/**
 * @author hosse
 * @created 02/08/2023 - 10:59 AM
 * @project Touristo
 */

interface BookmarkDomain : XDomain {

    suspend fun getBookmarks(): Flow<List<Bookmark>>

}