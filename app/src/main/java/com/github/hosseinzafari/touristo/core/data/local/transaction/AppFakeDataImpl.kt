package com.github.hosseinzafari.touristo.core.data.local.transaction

import com.github.hosseinzafari.touristo.core.data.local.domain.AppFakeDataDomain
import com.github.hosseinzafari.touristo.core.data.local.fake_data.FakeUser
import com.github.hosseinzafari.touristo.core.data.local.fake_data.toUserRealm
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.channels.awaitClose
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 04/05/2023 - 1:14 PM
 * @project Touristo
 */

class AppFakeDataImpl @Inject constructor(
) : AppFakeDataDomain {

    override suspend fun addFakeUsers(users: List<FakeUser>) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {transaction->
            transaction.insertOrUpdate(users.toUserRealm())
        }

        realm.close()
    }
}