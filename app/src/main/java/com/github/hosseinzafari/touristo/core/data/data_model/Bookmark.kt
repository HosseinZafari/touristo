package com.github.hosseinzafari.touristo.core.data.data_model

import com.github.hosseinzafari.touristo.core.data.dto.LocationModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 15/06/2023 - 6:55 PM
 * @project Touristo
 */
data class Bookmark(
    val id: Int  ,
    val createdAt: String? ,
    val userID: String ,
    val location: Location ,
)


