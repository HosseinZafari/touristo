package com.github.hosseinzafari.touristo.presentation.screens.add_location

import android.net.Uri
import com.github.hosseinzafari.touristo.base.system.mvi.XAction
import com.github.hosseinzafari.touristo.base.system.mvi.XEffect
import com.github.hosseinzafari.touristo.base.system.mvi.XState
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.data_model.CategoryModel
import com.github.hosseinzafari.touristo.core.data.data_model.ProvinceModel

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 24/06/2023 - 8:29 PM
 * @project Touristo
 */



data class AddLocationState(
    var province: ProvinceModel?  ,
    var category: CategoryModel?  ,
    var name: String ,
    var description: String ,
    var pictureUrl: Uri? ,
    override var status: XStatus,
    override var effects: AddLocationEffect?
) : XState<AddLocationEffect>


sealed class AddLocationEffect: XEffect {
    object NavigateToHome : AddLocationEffect()
}

sealed class AddLocationAction: XAction {

    data class OnChangeName(val newText: String) : AddLocationAction()
    data class OnChangeDesc(val newText: String) : AddLocationAction()
    data class OnChangeProvince(val newLocation: ProvinceModel) : AddLocationAction()
    data class OnChangeCategory(val newCategory: CategoryModel) : AddLocationAction()
    data class OnChangePicture(val newPicture: Uri?) : AddLocationAction()

    object Submit : AddLocationAction()

}

