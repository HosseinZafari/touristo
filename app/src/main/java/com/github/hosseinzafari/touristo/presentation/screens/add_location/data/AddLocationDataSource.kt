package com.github.hosseinzafari.touristo.presentation.screens.add_location.data

import android.util.Log
import com.github.hosseinzafari.touristo.core.Bucket
import com.github.hosseinzafari.touristo.core.Tables
import com.github.hosseinzafari.touristo.core.data.data_model.Category
import com.github.hosseinzafari.touristo.core.data.dto.CategoryModel
import com.github.hosseinzafari.touristo.core.data.dto.LocationModelInsert
import com.github.hosseinzafari.touristo.core.data.dto.toCategory
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 06/08/2023 - 12:46 PM
 * @project Touristo
 */

class AddLocationDataSource @Inject constructor(
    val db: Postgrest,
    val auth: GoTrue,
    val storage: Storage,
) : AddLocationDomain {

    override suspend fun uploadLocationImage(file: File) : String {
        val user = auth.currentUserOrNull()
        if (user == null) {
            throw Exception("User Not Found")
        }

        val fileName = user.id + "." + file.name
        val result = storage[Bucket.Location.text].upload(fileName , file.readBytes() , upsert = true )
        Log.i("Test" , "result uploaded file key: $result filename $fileName")

        return storage[Bucket.Location.text].publicUrl(fileName)
    }


    override suspend fun addLocation(
        name: String,
        desc: String,
        provinceName: String,
        category: Category,
        imageUri: String
    ) {
        val user = auth.currentUserOrNull()
        if (user == null) {
            throw Exception("User Not Found")
        }

        db[Tables.Location.text].insert(
            LocationModelInsert(
                desc,
                name,
                provinceName,
                category.id,
                user.id,
                imageUri
            )
        )
    }

    override suspend fun getCategories() = flow {
        emit(
            db[Tables.Category.text].select()
                .decodeList<CategoryModel>()
                .toCategory()
        )
    }


}