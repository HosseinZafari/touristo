package com.github.hosseinzafari.touristo.core.data.local.domain

import com.github.hosseinzafari.touristo.base.system.data_layer.XDomain
import com.github.hosseinzafari.touristo.core.data.local.fake_data.FakeUser

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 04/05/2023 - 1:12 PM
 * @project Touristo
 */

interface AppFakeDataDomain : XDomain {
    suspend fun addFakeUsers(users: List<FakeUser>)
}