package com.github.hosseinzafari.touristo.core.data.data_model

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 30/07/2023 - 2:08 PM
 * @project Touristo
 */

data class Location(
    var id: Int,
    var desc: String,
    var name: String,
    var provinceName: String,
    var category: Category,
    var userId: User ,
    var likeCount: Int  = 0,
    var imageUri: String? = null,
)