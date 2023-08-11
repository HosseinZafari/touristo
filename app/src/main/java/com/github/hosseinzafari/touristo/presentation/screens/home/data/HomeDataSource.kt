package com.github.hosseinzafari.touristo.presentation.screens.home.data

import android.util.Log
import com.github.hosseinzafari.touristo.core.Tables
import com.github.hosseinzafari.touristo.core.data.dto.*
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Count
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

        val locations = db[Tables.Location.text].select(
            columns = Columns.list(
                "id",
                "created_at",
                "desc",
                "name",
                "image_uri",
                "user_id",
                "province_name",
                "category_model!inner(id,title,created_at)",
            )
        ) { eq("category_model.id", id) }.decodeList<LocationModel>()

        locations.map {
            it.likeCount = getLikeCount(it.id)
        }

        emit(locations.toLocation())
    }

    private suspend fun getLikeCount(locationID: Int): Int? {
        return db[Tables.Like.text].select(count = Count.EXACT) {
            eq("location_id", locationID)
        }.count()?.toInt()
    }

    override suspend fun getCategories() = flow {
        emit(
            db[Tables.Category.text].select()
                .decodeList<CategoryModel>()
                .toCategory()
        )
    }

    override suspend fun getBestDestinations() = flow {
        val destinations = db[Tables.BestDestination.text].select()


        Log.i("Test" , Tables.BestDestination.text + ":  detination " + destinations.body)

        emit(
            destinations.decodeList<BestDestinationModel>()
                .toBestDestination()
        )
    }
}