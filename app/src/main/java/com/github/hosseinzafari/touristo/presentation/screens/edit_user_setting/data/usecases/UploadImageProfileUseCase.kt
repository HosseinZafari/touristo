package com.github.hosseinzafari.touristo.presentation.screens.edit_user_setting.data.usecases

import com.github.hosseinzafari.touristo.base.system.data_layer.XUseCase
import com.github.hosseinzafari.touristo.presentation.screens.edit_user_setting.data.EditUserDomain
import java.io.File
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 08/08/2023 - 6:12 PM
 * @project Touristo
 */

class UploadImageProfileUseCase @Inject constructor(
    override val domain: EditUserDomain
) : XUseCase<EditUserDomain>() {

    suspend operator fun invoke(file: File) = domain.uploadUserImage(file)
}