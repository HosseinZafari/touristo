package com.github.hosseinzafari.touristo.presentation.screens.login.data.usecases

import com.github.hosseinzafari.touristo.base.system.data_layer.XUseCase
import com.github.hosseinzafari.touristo.presentation.screens.login.data.domain.LoginDomain
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 01/05/2023 - 11:19 PM
 * @project Touristo
 */

class FindUserUseCase @Inject constructor(
    override val domain: LoginDomain
) : XUseCase<LoginDomain>() {

    suspend operator fun invoke(email : String , password: String) = domain.findUser(email ,  password)
}