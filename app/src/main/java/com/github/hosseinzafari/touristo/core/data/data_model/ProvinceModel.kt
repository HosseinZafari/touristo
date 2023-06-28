package com.github.hosseinzafari.touristo.core.data.data_model

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 08/06/2023 - 10:40 AM
 * @project Touristo
 */

data class ProvinceModel (
    var id: Int ,
    var name: String ,
)

fun ProvinceModel.toMap() = Pair(id , name)
fun List<ProvinceModel>.toMap() : Map<Int,String>  {
    val map = mutableMapOf<Int,String>()
    onEach {
        map.put(it.id , it.name)
    }

    return map
}




val provinceData = listOf(
    ProvinceModel(1 , "تهران") ,
    ProvinceModel(2 , "خوزستان") ,
    ProvinceModel(3 , "بوشهر") ,
    ProvinceModel(4 , "خراسان رضوی") ,
    ProvinceModel(5 , "آذربایجان شرقی") ,
    ProvinceModel(6 , "مازندران") ,
    ProvinceModel(7 , "البرز") ,
    ProvinceModel(8 , "گیلان") ,
    ProvinceModel(9 , "کهگیلویه و بویراحمد") ,
    ProvinceModel(10, "آذربایجان غربی") ,
    ProvinceModel(11, "هرمزگان") ,
    ProvinceModel(12, "قزوین") ,
    ProvinceModel(13, "کرمانشاه") ,
    ProvinceModel(14, "مرکزی") ,
    ProvinceModel(15, "یزد") ,
    ProvinceModel(16, "سیستان و بلوچستان") ,
    ProvinceModel(17, "همدان") ,
    ProvinceModel(18, "ایلام") ,
    ProvinceModel(19, "گلستان") ,
    ProvinceModel(20, "لرستان") ,
    ProvinceModel(21, "زنجان") ,
    ProvinceModel(22, "اردبیل") ,
    ProvinceModel(23, "قم") ,
    ProvinceModel(24, "کردستان") ,
    ProvinceModel(25, "سمنان") ,
    ProvinceModel(26, "چهارمحال و بختیاری") ,
    ProvinceModel(27, "خراسان شمالی") ,
    ProvinceModel(28, "خراسان جنوبی") ,
    ProvinceModel(29, "فارس") ,
    ProvinceModel(30, "اصفهان") ,
    ProvinceModel(30, "کرمان") ,
)
