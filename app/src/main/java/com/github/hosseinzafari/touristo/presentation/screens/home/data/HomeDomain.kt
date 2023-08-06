package com.github.hosseinzafari.touristo.presentation.screens.home.data

import com.github.hosseinzafari.touristo.base.system.data_layer.XDomain
import com.github.hosseinzafari.touristo.core.data.data_model.BestDestination
import com.github.hosseinzafari.touristo.core.data.data_model.Category
import com.github.hosseinzafari.touristo.core.data.data_model.Location
import kotlinx.coroutines.flow.Flow

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 30/07/2023 - 2:17 PM
 * @project Touristo
 */

interface HomeDomain: XDomain {

    suspend fun getLocationByCategoryID(id: Int): Flow<List<Location>>

    suspend fun getCategories(): Flow<List<Category>>


    suspend fun getBestDestinations(): Flow<List<BestDestination>>
}