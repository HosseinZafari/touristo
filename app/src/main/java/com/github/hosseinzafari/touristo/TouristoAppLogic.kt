package com.github.hosseinzafari.touristo

import android.util.Log
import com.github.hosseinzafari.touristo.core.data.local.fake_data.fakeUsers
import com.github.hosseinzafari.touristo.core.data.local.usecases.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 05/05/2023 - 5:37 PM
 * @project Touristo
 */

class TouristoAppLogic @Inject constructor(
    private val isMigratedFakeDataUseCase: IsMigratedFakeDataUseCase ,
    private val setMigratedFakeDataUseCase: SetMigratedFakeDataUseCase ,
    private val addFakeUsersUseCase: AddFakeUsersUseCase ,
) {

    suspend fun migrateFakeData() {
        isMigratedFakeDataUseCase().also { migrated ->
            Log.i("Test", "migrated collectLatest " + migrated)
            if (!migrated) { // do migrate data fakes
                addFakeUsersUseCase(fakeUsers)
                setMigratedFakeDataUseCase(true)
            }

        }
    }

}