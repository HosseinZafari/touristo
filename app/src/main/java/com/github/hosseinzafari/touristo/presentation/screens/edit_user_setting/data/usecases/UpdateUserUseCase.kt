package com.github.hosseinzafari.touristo.presentation.screens.edit_user_setting.data.usecases

import com.github.hosseinzafari.touristo.base.system.data_layer.XUseCase
import com.github.hosseinzafari.touristo.core.data.data_model.User
import com.github.hosseinzafari.touristo.presentation.screens.edit_user_setting.data.EditUserDomain
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 08/08/2023 - 2:18 PM
 * @project Touristo
 */

class UpdateUserUseCase @Inject constructor(
    override val domain: EditUserDomain
) : XUseCase<EditUserDomain> (){

    suspend operator fun invoke(newUser: User) = domain.updateUser(newUser)
}