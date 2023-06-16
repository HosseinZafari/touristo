package com.github.hosseinzafari.touristo.presentation.screens.location_make

import android.util.Log
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 06/06/2023 - 2:47 PM
 * @project Touristo
 */


@HiltViewModel
class LocationMakeViewModel @Inject constructor() : XViewModel<LocationMakeEffect , LocationMakeAction , LocationMakeState>() {
    override val processor = processor(
        initialState = LocationMakeState(
            name = "" ,
            desc = "" ,
            imageUrl = "" ,
            provinceLocationID = 0 ,
            status = XStatus.Idle ,
            effects = null
        ) ,
        actionReducer = ::reducer
    )


    private fun reducer(oldState: LocationMakeState , action : LocationMakeAction) {
        when(action) {
            is LocationMakeAction.OnChangeNameProvinceLocation ->
                processor.setState(oldState.copy(provinceLocationID = action.id))
            is LocationMakeAction.OnChangeDescTextField ->
                processor.setState(oldState.copy(desc = action.text))
            is LocationMakeAction.OnChangeImageURLTextField ->
                processor.setState(oldState.copy(imageUrl = action.text))
            is LocationMakeAction.OnChangeNameTextField ->
                processor.setState(oldState.copy(name = action.text))

            is LocationMakeAction.Submit -> {
                Log.i("Test" , "submited")
                processor.setState(oldState.copy(status = XStatus.Loading))

                // TODO : call api
            }
        }
    }

}