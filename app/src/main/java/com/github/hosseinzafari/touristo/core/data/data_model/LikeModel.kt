package com.github.hosseinzafari.touristo.core.data.data_model

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 15/06/2023 - 6:54 PM
 * @project Touristo
 */
 
data class LikeModel(
    val id: Int ,
    val userID: Int ,
    val locationID: Int ,
)

var LikeData : MutableList<LikeModel> = mutableListOf()
