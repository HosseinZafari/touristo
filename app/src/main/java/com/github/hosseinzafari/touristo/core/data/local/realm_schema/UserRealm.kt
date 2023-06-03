package com.github.hosseinzafari.touristo.core.data.local.realm_schema

import com.github.hosseinzafari.touristo.core.data.data_model.User
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 26/04/2023 - 3:02 PM
 * @project Touristo
 */

open class UserRealm (
    @PrimaryKey()
    var id: String? = ObjectId().toHexString() ,
    var name: String? = null  ,
    var email: String? = null,
    var password: String? = null,
    var profileUrl: String? = null ,
): RealmObject()


fun UserRealm.toUser() = User(id , name , email , password , profileUrl)
fun User.toUserRealm() = UserRealm(id ,name , email, profileUrl)