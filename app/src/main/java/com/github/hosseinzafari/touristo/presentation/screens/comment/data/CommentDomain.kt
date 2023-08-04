package com.github.hosseinzafari.touristo.presentation.screens.comment.data

import com.github.hosseinzafari.touristo.base.system.data_layer.XDomain
import com.github.hosseinzafari.touristo.core.data.data_model.Comment
import kotlinx.coroutines.flow.Flow

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 04/08/2023 - 11:59 AM
 * @project Touristo
 */
 
interface CommentDomain: XDomain {

    suspend fun getComments(locationID: Int) : Flow<List<Comment>>


    suspend fun addComment(locationID: Int , text: String)
}