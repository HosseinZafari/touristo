package com.github.hosseinzafari.touristo.core.data.dto

import com.github.hosseinzafari.touristo.core.data.data_model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 04/08/2023 - 3:02 PM
 * @project Touristo
 */

@Serializable
data class UserModel (
    @SerialName("id")
    var id: String ,
    @SerialName("name")
    var name: String? ,
    @SerialName("family")
    var family: String? ,
    @SerialName("desc")
    var desc: String? ,
    @SerialName("email")
    var email: String ,
    @SerialName("image_uri")
    var imageUri: String? ,
)

fun UserModel.toUser() = User(id , name , family , desc ,  email , null , imageUri)