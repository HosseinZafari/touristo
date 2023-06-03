package com.github.hosseinzafari.touristo

import com.github.hosseinzafari.touristo.core.data.local.usecases.IsUserLoginedUseCase
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 17/05/2023 - 4:43 PM
 * @project Touristo
 */

class MainActivityLogic @Inject constructor(
    private val isUserLoginedUseCase: IsUserLoginedUseCase ,
) {

    suspend fun isUserLogined() = isUserLoginedUseCase()


}