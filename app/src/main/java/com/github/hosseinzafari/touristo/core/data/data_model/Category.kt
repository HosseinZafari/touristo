package com.github.hosseinzafari.touristo.core.data.data_model

data class Category(
    val id: Int ,
    val title: String,
)


val categories: List<Category> = listOf(
    Category(0 , "جنگل") ,
    Category(1 , "دریا") ,
    Category(2 , "کوه") ,
    Category(3 , "ساحل") ,
)



