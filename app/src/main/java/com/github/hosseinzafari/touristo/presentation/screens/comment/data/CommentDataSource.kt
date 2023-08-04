package com.github.hosseinzafari.touristo.presentation.screens.comment.data

import android.util.Log
import com.github.hosseinzafari.touristo.core.data.dto.CommentModel
import com.github.hosseinzafari.touristo.core.data.dto.CommentModelInsert
import com.github.hosseinzafari.touristo.core.data.dto.toComment
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Returning
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 04/08/2023 - 12:00 PM
 * @project Touristo
 */

class CommentDataSource @Inject constructor(
    val db: Postgrest,
    val auth: GoTrue,
) : CommentDomain {

    override suspend fun getComments(locationID: Int) = flow {
        val result = db["comment_model"].select(
            Columns.list(
                "id", "text", "location_id",
                "users!inner(id,name,image_uri,email)",
            )
        ) {
            eq("location_id", locationID)
        }


        Log.i("Test", "getComments body : " + result.body)

        emit(
            result.decodeList<CommentModel>().toComment()
        )
    }

    override suspend fun addComment(locationID: Int, text: String) {
        val user = auth.currentUserOrNull()
        if (user == null) {
            throw Exception("Current User Not Found")
        }

        db["comment_model"].insert(
            CommentModelInsert(userId = user.id, text = text, locationID = locationID),
            returning = Returning.MINIMAL
        )


    }


}