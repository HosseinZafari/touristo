package com.github.hosseinzafari.touristo.core.data.dto

import com.github.hosseinzafari.touristo.core.data.data_model.Location
import com.github.hosseinzafari.touristo.core.data.data_model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 08/06/2023 - 10:28 AM
 * @project Touristo
 */
@Serializable
data class LocationModel(
    @SerialName("id")
    var id: Int,
    @SerialName("desc")
    var desc: String,
    @SerialName("name")
    var name: String,
    @SerialName("province_name")
    var provinceName: String,
    @SerialName("category_model")
    var categoryModel: CategoryModel,
    @SerialName("user_id")
    var userId: String,
//    var comments: MutableList<CommentModel>,
    @SerialName("image_uri")
    var imageUri: String? = null,
    @SerialName("created_at")
    var createdAt: String? = null,
    var likeCount: Int? = 0,
)

fun LocationModel.toLocation() = Location(
    id,
    desc,
    name,
    provinceName,
    categoryModel.toCategory()  ,
    User(userId, "" , "" , null , null),
    if (likeCount == null) 0 else likeCount!! ,
    imageUri,
)

fun List<LocationModel>.toLocation(): List<Location> = map {
    it.toLocation()
}

fun Location.toLocationModel() = LocationModel(
    id = id,
    desc = desc,
    name = name,
    provinceName = provinceName,
    categoryModel = category.toCategoryModel()  ,
    userId = userId.id + "" ,
    imageUri = imageUri,
    likeCount = likeCount ,
)

fun List<Location>.toLocationModel(): List<LocationModel> = map {
    it.toLocationModel()
}