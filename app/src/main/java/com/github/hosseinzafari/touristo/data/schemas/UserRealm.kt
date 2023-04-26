package com.github.hosseinzafari.touristo.data.schemas

import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 26/04/2023 - 3:02 PM
 * @project Touristo
 */

data class UserRealm (
    @PrimaryKey()
    val id: String = ObjectId().toHexString() ,
    val name: String  ,
    val email: String ,
    val profileUrl: String ,
)