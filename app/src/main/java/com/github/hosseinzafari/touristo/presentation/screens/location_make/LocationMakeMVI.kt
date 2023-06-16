package com.github.hosseinzafari.touristo.presentation.screens.location_make

import com.github.hosseinzafari.touristo.base.system.mvi.XAction
import com.github.hosseinzafari.touristo.base.system.mvi.XEffect
import com.github.hosseinzafari.touristo.base.system.mvi.XState
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 06/06/2023 - 2:48 PM
 * @project Touristo
 */

/*
* States of the Location Make Screen
* */
data class LocationMakeState(
    val name: String ,
    val imageUrl: String ,
    val desc: String ,
    val provinceLocationID : Int ,
    override var status: XStatus,
    override val effects: LocationMakeEffect?
) : XState<LocationMakeEffect>

/*
* User Action In Location Make Screen
* */
sealed class LocationMakeAction : XAction {
    data class OnChangeNameTextField(
        val text: String
    ) : LocationMakeAction()

    data class OnChangeImageURLTextField(
        val text: String
    ) : LocationMakeAction()

    data class OnChangeDescTextField(
        val text: String
    ) : LocationMakeAction()

    data class OnChangeNameProvinceLocation(
        val id: Int
    ) : LocationMakeAction()


    object Submit : LocationMakeAction()

}

/*
* System Effect Returned in the Location Make Screen
* */
sealed class LocationMakeEffect : XEffect {
    object NavigateToHome : LocationMakeEffect()
}