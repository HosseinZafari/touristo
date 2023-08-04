package com.github.hosseinzafari.touristo.core.data.dto

import com.github.hosseinzafari.touristo.core.data.data_model.Category
import com.github.hosseinzafari.touristo.core.data.local.DataStoreKeys.id
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryModel(
    @SerialName("id")
    val id: Int ,
    @SerialName("title")
    val title: String,
    @SerialName("created_at")
    val createdAt: String,
)

fun CategoryModel.toCategory() = Category(id, title)

fun List<CategoryModel>.toCategory() = map {
    it.toCategory()
}

fun Category.toCategoryModel() = CategoryModel(id, title , "")

fun List<Category>.toCategoryModel() = map {
    it.toCategoryModel()
}