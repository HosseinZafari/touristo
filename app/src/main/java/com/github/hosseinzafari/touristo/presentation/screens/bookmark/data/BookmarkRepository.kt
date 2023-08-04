package com.github.hosseinzafari.touristo.presentation.screens.bookmark.data

import com.github.hosseinzafari.touristo.core.data.data_model.Bookmark
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 02/08/2023 - 10:59 AM
 * @project Touristo
 */

class BookmarkRepository @Inject constructor(
    val dataSource: BookmarkDataSource
) : BookmarkDomain {

    override suspend fun getBookmarks() = dataSource.getBookmarks()


}