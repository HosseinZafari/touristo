package com.github.hosseinzafari.touristo.presentation.screens.bookmark.data

import android.util.Log
import com.github.hosseinzafari.touristo.core.data.data_model.Bookmark
import com.github.hosseinzafari.touristo.core.data.dto.BookmarkModel
import com.github.hosseinzafari.touristo.core.data.dto.toBookmark
import com.github.hosseinzafari.touristo.core.data.dto.toBookmarkModel
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Returning
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


        val result = db["bookmark_model"].select(
            Columns.list("id", "user_id", "created_at", "location_model!inner(id,name,desc,created_at,user_id,like_count,image_uri,province_name,category_model!inner(id,title,created_at))")
        ) {
            eq("user_id" , user.id)
        }

        Log.i("Test" , "bookmarks : body " + result.body)

        emit(
            result.decodeList<BookmarkModel>().toBookmark()
        )
    }



}