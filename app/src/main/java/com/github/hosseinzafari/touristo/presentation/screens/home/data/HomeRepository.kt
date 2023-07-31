package com.github.hosseinzafari.touristo.presentation.screens.home.data

import com.github.hosseinzafari.touristo.core.data.data_model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 30/07/2023 - 2:21 PM
 * @project Touristo
 */


class HomeRepository @Inject constructor(
    val dataSource: HomeDataSource
): HomeDomain {

    override suspend fun getLocationByCategoryID(id: Int) = dataSource.getLocationByCategoryID(id)
    override suspend fun getCategories() = dataSource.getCategories()
}