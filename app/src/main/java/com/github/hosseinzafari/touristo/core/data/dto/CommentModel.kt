package com.github.hosseinzafari.touristo.core.data.dto

import com.github.hosseinzafari.touristo.core.data.data_model.Comment
import com.github.hosseinzafari.touristo.core.data.data_model.User
import io.github.jan.supabase.gotrue.user.UserInfo
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
    var id: Int,
    @SerialName("users")
    var user: UserModel ,
    @SerialName("text")
    var text: String,
    @SerialName("location_id")
    var locationID: Int? = null,
    @SerialName("location_model")
    var location: LocationModel? = null,
)
@Serializable
data class CommentModelInsert(
    @SerialName("id")
    var id: Int? = null,
    @SerialName("user_id")
    var userId: String,
    @SerialName("text")
    var text: String,
    @SerialName("location_id")
    var locationID: Int  ,
)


fun CommentModel.toComment() = Comment(id, user.toUser(), text, locationID, location?.toLocation())


fun List<CommentModel>.toComment() = map { it.toComment() }





