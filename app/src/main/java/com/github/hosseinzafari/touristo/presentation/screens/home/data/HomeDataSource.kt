package com.github.hosseinzafari.touristo.presentation.screens.home.data

import android.util.Log
import com.github.hosseinzafari.touristo.core.data.dto.CategoryModel
import com.github.hosseinzafari.touristo.core.data.dto.LocationModel
import com.github.hosseinzafari.touristo.core.data.dto.toCategory
import com.github.hosseinzafari.touristo.core.data.dto.toLocation
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 30/07/2023 - 2:25 PM
 * @project Touristo
 */

class HomeDataSource @Inject constructor(
    val db: Postgrest
) : HomeDomain {

    override suspend fun getLocationByCategoryID(id: Int) = flow {
        Log.i("Test", "Start getLocationByCategoryID() ")

        emit(
            db["location_model"].select(
                columns = Columns.list(
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
            ) { eq("category_model.id", id) }.decodeList<LocationModel>().toLocation()
        )
    }

    override suspend fun getCategories() = flow {
        emit(
            db["category_model"].select()
                .decodeList<CategoryModel>()
                .toCategory()
        )
    }
}