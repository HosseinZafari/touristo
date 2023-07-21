package com.github.hosseinzafari.touristo.core.data.data_model

import kotlinx.serialization.Serializable

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 05/05/2023 - 2:06 PM
 * @project Touristo
 */

@Serializable
data class User (
    var id: String?  ,
    var name: String?,
    var email: String?,
    var password: String?,
    var profileUrl: String?,
)