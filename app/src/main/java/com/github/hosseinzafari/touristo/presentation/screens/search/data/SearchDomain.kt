package com.github.hosseinzafari.touristo.presentation.screens.search.data

import com.github.hosseinzafari.touristo.base.system.data_layer.XDomain
import com.github.hosseinzafari.touristo.core.data.data_model.Location
import kotlinx.coroutines.flow.Flow

/**
 * @author hosse
 * @created 01/08/2023 - 11:45 AM
 * @project Touristo
 */

interface SearchDomain : XDomain {

    suspend fun search(text: String) : Flow<List<Location>>

}