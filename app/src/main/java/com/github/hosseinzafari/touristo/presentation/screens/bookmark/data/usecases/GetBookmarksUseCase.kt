package com.github.hosseinzafari.touristo.presentation.screens.bookmark.data.usecases

import com.github.hosseinzafari.touristo.base.system.data_layer.XUseCase
import com.github.hosseinzafari.touristo.presentation.screens.bookmark.data.BookmarkDomain
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 02/08/2023 - 11:55 AM
 * @project Touristo
 */

class GetBookmarksUseCase @Inject constructor(
    override val domain: BookmarkDomain
) : XUseCase<BookmarkDomain>() {

    suspend operator fun invoke() = domain.getBookmarks()

}