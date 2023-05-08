package com.github.hosseinzafari.touristo.core.data.local.usecases

import com.github.hosseinzafari.touristo.base.system.data_layer.XUseCase
import com.github.hosseinzafari.touristo.core.data.local.domain.AppFakeDataDomain
import com.github.hosseinzafari.touristo.core.data.local.fake_data.FakeUser
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 04/05/2023 - 1:50 PM
 * @project Touristo
 */


class AddFakeUsersUseCase  @Inject constructor(
    override val domain: AppFakeDataDomain
): XUseCase<AppFakeDataDomain>() {

    operator suspend fun invoke(users: List<FakeUser>) = domain.addFakeUsers(users)
}