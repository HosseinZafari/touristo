package com.github.hosseinzafari.touristo.presentation.screens.location_description.data

import com.github.hosseinzafari.touristo.core.data.data_model.Bookmark
import com.github.hosseinzafari.touristo.core.data.dto.*
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Count
import io.github.jan.supabase.postgrest.query.Returning
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 02/08/2023 - 1:20 PM
 * @project Touristo
 */

class LocationDescDataSource @Inject constructor(
    val db: Postgrest,
    val auth: GoTrue,
) : LocationDescDomain {

    override suspend fun getLocation(id: Int) = flow {
        emit(
            db["location_model"].select(
                Columns.list(
                    "id",
                    "created_at",
                    "desc",
                    "name",
                    "like_count",
                    "image_uri",
                    "user_id",
                    "province_name",
                    "category_model!inner(id,title,created_at)",
                )
            ) {
                eq("id", id)
            }.decodeSingle<LocationModel>().toLocation()
        )
    }

    override suspend fun bookmarkIsExists(locationID: Int) = flow {
        val user = auth.currentUserOrNull()
        if (user == null) {
            throw Exception("User Not Found")
        }

        val count = db["bookmark_model"].select(count = Count.EXACT) {
            eq("location_id" , locationID)
            eq("user_id" , user.id)
        }.count()!!

        if (count > 0L) {
            emit(true)
        } else {
            emit(false)
        }
    }


    override suspend fun addBookmark(locationID: Int) {
        val user = auth.currentUserOrNull()
        if (user == null) {
            throw Exception("User Not Found")
        }

        db["bookmark_model"].insert(
            value = BookmarkModelInsert(userID = user.id  , locationID = locationID),
            returning = Returning.MINIMAL
        )
    }

    override suspend fun removeBookmark(locationID: Int) {
        val user = auth.currentUserOrNull()
        if (user == null) {
            throw Exception("User Not Found")
        }

        db["bookmark_model"].delete() {
            eq("user_id" , user.id)
            eq("location_id" , locationID)
        }
    }

    override suspend fun likeIsExists(locationID: Int) = flow  {
        val user = auth.currentUserOrNull()
        if (user == null) {
            throw Exception("User Not Found")
        }

        val count = db["like_model"].select(count = Count.EXACT) {
            eq("location_id" , locationID)
            eq("user_id" , user.id)
        }.count()!!

        if (count > 0L) {
            emit(true)
        } else {
            emit(false)
        }
    }

    override suspend fun addLike(locationID: Int) {
        val user = auth.currentUserOrNull()
        if (user == null) {
            throw Exception("User Not Found")
        }

        db["like_model"].insert(
            LikeModelInsert( userID = user.id , locationID = locationID) ,
            returning = Returning.MINIMAL,
        )
    }

    override suspend fun removeLike(locationID: Int) {
        val user = auth.currentUserOrNull()
        if (user == null) {
            throw Exception("User Not Found")
        }

        db["like_model"].delete() {
            eq("user_id" , user.id)
            eq("location_id" , locationID)
        }
    }
}