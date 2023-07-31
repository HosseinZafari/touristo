package com.github.hosseinzafari.touristo.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 08/06/2023 - 10:59 AM
 * @project Touristo
 */
@Serializable
data class CommentModel(
   @SerialName("id")
   var id: Int ,
   @SerialName("user_id")
   var userId: String ,
   @SerialName("text")
   var text: String ,
)


