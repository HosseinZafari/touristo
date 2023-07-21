package com.github.hosseinzafari.touristo.core

import com.github.hosseinzafari.touristo.core.data.local.usecases.IsUserLoggedInUseCase
import com.github.hosseinzafari.touristo.core.data.local.usecases.SaveUserUseCase
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 17/05/2023 - 4:43 PM
 * @project Touristo
 */

class MainActivityLogic @Inject constructor(
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase,
    private val saveUserUseCase: SaveUserUseCase,
) {

    suspend fun isUserLoggedIn() = isUserLoggedInUseCase()
    suspend fun saveUser(id: String , email: String , name: String) = saveUserUseCase(id , email , name)


}