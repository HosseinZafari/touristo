package com.github.hosseinzafari.touristo.core.data.data_model

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 08/06/2023 - 10:59 AM
 * @project Touristo
 */
data class Comment (
   var id: Int ,
   var user: User ,
   var text: String ,
   var locationID: Int? ,
   var location: Location? ,
)



