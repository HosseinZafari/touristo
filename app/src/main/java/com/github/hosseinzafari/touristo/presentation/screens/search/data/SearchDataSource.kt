package com.github.hosseinzafari.touristo.presentation.screens.search.data

import com.github.hosseinzafari.touristo.core.data.dto.LocationModel
import com.github.hosseinzafari.touristo.core.data.dto.toLocation
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Count
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 01/08/2023 - 11:47 AM
 * @project Touristo
 */

class SearchDataSource @Inject constructor(
    val db: Postgrest
) : SearchDomain {

    override suspend fun search(text: String) = flow {
        var locations = db["location_model"].select(
            columns = Columns.list(
                "id",
                "created_at",
                "desc",
                "name",
                "image_uri",
                "user_id",
                "province_name",
                "category_model!inner(id,title,created_at)"
            )
        ) {
            or {
                like("desc", "%$text%")
                like("name", "%$text%")
                like("province_name", "%$text%")
            }
        }.decodeList<LocationModel>()


        locations.map {
            it.likeCount = getLikePerLocation(it.id)
        }

        emit(locations.toLocation())
    }

    private suspend fun getLikePerLocation(locationID: Int): Int? {
        return db["like_model"].select(count = Count.EXACT) {
            eq("location_id", locationID)
        }.count()?.toInt()
    }

}