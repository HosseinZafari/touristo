package com.github.hosseinzafari.touristo.presentation.screens.add_location.data

import com.github.hosseinzafari.touristo.core.data.data_model.Category
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 06/08/2023 - 12:47 PM
 * @project Touristo
 */

class AddLocationRepository @Inject constructor(
    val dataSource: AddLocationDataSource ,
): AddLocationDomain {
    override suspend fun uploadLocationImage(file: File) = dataSource.uploadLocationImage(file)

    override suspend fun addLocation(
        name: String,
        desc: String,
        provinceName: String,
        category: Category,
        imageUri: String
    )  = dataSource.addLocation(name, desc, provinceName, category, imageUri)

    override suspend fun getCategories() = dataSource.getCategories()
}