package com.github.hosseinzafari.touristo.presentation.screens.add_location.data

import com.github.hosseinzafari.touristo.base.system.data_layer.XDomain
import com.github.hosseinzafari.touristo.core.data.data_model.Category
import kotlinx.coroutines.flow.Flow
import java.io.File

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 06/08/2023 - 12:45 PM
 * @project Touristo
 */

interface AddLocationDomain : XDomain {

    suspend fun uploadLocationImage(file: File): String

    suspend fun addLocation(
        name: String,
        desc: String,
        provinceName: String,
        category: Category,
        imageUri: String,
    )


    suspend fun getCategories() : Flow<List<Category>>
}