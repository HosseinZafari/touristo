package com.github.hosseinzafari.touristo.core.data.dto

import com.github.hosseinzafari.touristo.core.data.data_model.Like
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
    val userID: String ,
    @SerialName("location_model")
    val location: LocationModel ,
)

@Serializable
data class LikeModelInsert(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("user_id")
    val userID: String  ,
    @SerialName("location_id")
    val locationID: Int  ,
)


fun LikeModel.toLike() = Like(id , userID , location.toLocation())
fun List<LikeModel>.toLike() = map { it.toLike() }


fun Like.toLikeModel() = LikeModel(id , userID , location.toLocationModel())
fun List<Like>.toLikeModel() = map { it.toLikeModel() }

