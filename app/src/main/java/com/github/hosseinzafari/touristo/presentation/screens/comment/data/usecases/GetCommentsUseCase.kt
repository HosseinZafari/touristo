package com.github.hosseinzafari.touristo.presentation.screens.comment.data.usecases

import com.github.hosseinzafari.touristo.base.system.data_layer.XUseCase
import com.github.hosseinzafari.touristo.presentation.screens.comment.data.CommentDomain
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 04/08/2023 - 12:11 PM
 * @project Touristo
 */

class GetCommentsUseCase @Inject constructor(
    override val domain: CommentDomain
) : XUseCase<CommentDomain>() {


    suspend operator fun invoke(locationID: Int) = domain.getComments(locationID)

}