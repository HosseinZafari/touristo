package com.github.hosseinzafari.touristo.presentation.screens.signup.data

import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 10/05/2023 - 12:11 AM
 * @project Touristo
 */

class SignupRepo @Inject constructor(
    private val remote: SignupDataSource,
) : SignupDomain {
    override suspend fun signup(email: String, password: String ) = remote.signup(email , password )
}