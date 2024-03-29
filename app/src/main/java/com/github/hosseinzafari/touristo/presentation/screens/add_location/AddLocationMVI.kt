package com.github.hosseinzafari.touristo.presentation.screens.add_location

import android.net.Uri
import com.github.hosseinzafari.touristo.base.system.mvi.XAction
import com.github.hosseinzafari.touristo.base.system.mvi.XEffect
import com.github.hosseinzafari.touristo.base.system.mvi.XState
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.data_model.Category
import com.github.hosseinzafari.touristo.core.data.dto.CategoryModel
import com.github.hosseinzafari.touristo.core.data.dto.ProvinceModel

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 24/06/2023 - 8:29 PM
 * @project Touristo
 */



data class AddLocationState(
    var selectedProvince: ProvinceModel?,
    var selectedCategory: Category ?,
    var name: String,
    var description: String,
    var pictureUrl: Uri?,
    var categories: List<Category> ,
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
    data class OnChangeCategory(val newCategory: Category ) : AddLocationAction()
    data class OnChangePicture(val newPicture: Uri?) : AddLocationAction()

    object Submit : AddLocationAction()
    object GetData : AddLocationAction()

}

