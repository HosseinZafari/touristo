package com.github.hosseinzafari.touristo.core.data.data_model

data class CategoryModel(
    val id: Int ,
    val title: String,
)


val categories: List<CategoryModel> = listOf(
    CategoryModel(0 , "جنگل") ,
    CategoryModel(1 , "دریا") ,
    CategoryModel(2 , "کوه") ,
    CategoryModel(3 , "ساحل") ,
)



