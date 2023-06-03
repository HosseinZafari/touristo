package com.github.hosseinzafari.touristo.presentation.screens.login.data.usecases

import com.github.hosseinzafari.touristo.base.system.data_layer.XUseCase
import com.github.hosseinzafari.touristo.presentation.screens.login.data.LoginDomain
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 01/05/2023 - 11:19 PM
 * @project Touristo
 */


class SaveUserInfoUseCase @Inject constructor(
    override val domain: LoginDomain
) : XUseCase<LoginDomain>() {

    suspend operator fun invoke(id: String , name: String , email : String , isLogin: Boolean , loginAuthTime: String) = domain.saveUserInfo(id , name , email , isLogin , loginAuthTime)
}