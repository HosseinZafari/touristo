package com.github.hosseinzafari.touristo.presentation.screens.signup.data.usecases

import com.github.hosseinzafari.touristo.base.system.data_layer.XUseCase
import com.github.hosseinzafari.touristo.core.data.data_model.User
import com.github.hosseinzafari.touristo.presentation.screens.signup.data.SignupDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 10/05/2023 - 10:03 PM
 * @project Touristo
 */
 
class SingupUseCase @Inject constructor(
    override val domain: SignupDomain
) : XUseCase<SignupDomain>() {

    suspend operator fun invoke(user: User) = domain.signup(user)
}