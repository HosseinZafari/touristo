package com.github.hosseinzafari.touristo.presentation.screens.comment.data

import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 04/08/2023 - 11:59 AM
 * @project Touristo
 */

class CommentRepository @Inject constructor(
    val dataSource: CommentDataSource,
) : CommentDomain {

    override suspend fun getComments(locationID: Int) = dataSource.getComments(locationID)

    override suspend fun addComment(locationID: Int, text: String) =
        dataSource.addComment(locationID, text)

}