package com.github.hosseinzafari.touristo.presentation.screens.bookmark.data

import com.github.hosseinzafari.touristo.core.data.dto.BookmarkModel
import com.github.hosseinzafari.touristo.core.data.dto.toBookmark
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Count
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 02/08/2023 - 10:59 AM
 * @project Touristo
 */

class BookmarkDataSource @Inject constructor(
    val db: Postgrest ,
    val auth: GoTrue,
) : BookmarkDomain {

    override suspend fun getBookmarks() = flow {
        val user = auth.currentUserOrNull()
        if(user == null) {
            throw Exception("Current User Not Found")
        }


        val bookmarks = db["bookmark_model"].select(
            Columns.list("id", "user_id", "created_at", "location_model!inner(id,name,desc,created_at,user_id, image_uri,province_name,category_model!inner(id,title,created_at))")
        ) {
            eq("user_id" , user.id)
        }.decodeList<BookmarkModel>()

        bookmarks.map {
            it.location.likeCount = getLikeCount(it.location.id)
        }

        emit(
            bookmarks.toBookmark()
        )
    }

    private suspend fun getLikeCount(locationID: Int) : Int ? {
        return db["like_model"].select(count = Count.EXACT) {
            eq("location_id", locationID)
        }.count()?.toInt()
    }


}