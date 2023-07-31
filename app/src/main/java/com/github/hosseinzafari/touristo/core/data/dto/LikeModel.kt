package com.github.hosseinzafari.touristo.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 15/06/2023 - 6:54 PM
 * @project Touristo
 */

@Serializable
data class LikeModel(
    @SerialName("id")
    val id: Int ,
    @SerialName("user_id")
    val userID: Int ,
    @SerialName("location_id")
    val locationID: Int ,
)

var LikeData : MutableList<LikeModel> = mutableListOf()
