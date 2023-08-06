package com.github.hosseinzafari.touristo.core.data.dto

import com.github.hosseinzafari.touristo.core.data.data_model.BestDestination
import com.github.hosseinzafari.touristo.core.data.local.DataStoreKeys.id
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 08/06/2023 - 10:40 AM
 * @project Touristo
 */
@Serializable
data class BestDestinationModel (
    @SerialName("id")
    var id: Int ,
    @SerialName("province_name")
    var provinceName: String ,
    @SerialName("image_uri")
    var imageUri: String ,
    @SerialName("created_at")
    var createdAt: String ,
)


fun BestDestinationModel.toBestDestination() = BestDestination(id , provinceName , imageUri)


fun List<BestDestinationModel>.toBestDestination() = map {
    BestDestination(it.id , it.provinceName , it.imageUri)
}


