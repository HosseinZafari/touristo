package com.github.hosseinzafari.touristo.core.data.data_model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 15/06/2023 - 6:54 PM
 * @project Touristo
 */

data class Like(
    val id: Int  ,
    val userID: String ,
    val location: Location ,
)
