package com.github.hosseinzafari.touristo.presentation.screens.search.data

import com.github.hosseinzafari.touristo.core.data.data_model.Location
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 01/08/2023 - 11:46 AM
 * @project Touristo
 */

class SearchRepository @Inject constructor(
    val dataSource: SearchDataSource ,
) : SearchDomain {

    override suspend fun search(text: String) = dataSource.search(text)

}