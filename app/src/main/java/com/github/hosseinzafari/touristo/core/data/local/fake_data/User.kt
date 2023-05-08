package com.github.hosseinzafari.touristo.core.data.local.fake_data

import com.github.hosseinzafari.touristo.core.data.local.realm_schema.UserRealm

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 03/05/2023 - 5:18 PM
 * @project Touristo
 */
 
data class  FakeUser (
    val name: String ,
    val email: String ,
    val password: String ,
    val profileUrl: String ,
    val id: String  ,
)

fun FakeUser.toUserRealm() = UserRealm(id = id , name = name , email = email, password = password , profileUrl = profileUrl)
fun Collection<FakeUser>.toUserRealm() = this.map { it.toUserRealm() }

val fakeUsers = listOf(
    FakeUser(
        name = "حسین ظفری" ,
        email = "ho3einzafari@gmail.com" ,
        password = "hossein1112" ,
        id = "1" ,
        profileUrl = "",
    ) ,
    FakeUser(
        name = "علی حیدری" ,
        email = "ali@gmail.com" ,
        password = "ali1112" ,
        id = "2" ,
        profileUrl = "",
    ) ,
    FakeUser(
        name = "رضا بیگی" ,
        email = "reza@gmail.com" ,
        password = "reza1112" ,
        id = "3" ,
        profileUrl = "",
    ) ,
    FakeUser(
        name = "مینا گودرزی" ,
        email = "mina@gmail.com" ,
        password = "mina1112" ,
        id = "4" ,
        profileUrl = "",
    ) ,
    FakeUser(
        name = "جواد جوادی" ,
        email = "javad@gmail.com" ,
        password = "javad1112" ,
        id = "5" ,
        profileUrl = "",
    ) ,
    FakeUser(
        name = "مونا بخشی" ,
        email = "mona@gmail.com" ,
        password = "mona1112" ,
        id = "6" ,
        profileUrl = "",
    ) ,
)