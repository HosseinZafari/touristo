package com.github.hosseinzafari.touristo.core.data.data_model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 08/06/2023 - 10:40 AM
 * @project Touristo
 */
data class BestDestination (
    var id: Int ,
    var provinceName: String ,
    var imageUri: String ,
)
