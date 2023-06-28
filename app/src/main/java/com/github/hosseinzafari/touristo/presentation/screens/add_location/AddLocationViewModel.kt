package com.github.hosseinzafari.touristo.presentation.screens.add_location

import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.base.system.mvi.XProcessor
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.data_model.LocationData
import com.github.hosseinzafari.touristo.core.data.data_model.LocationModel
import com.github.hosseinzafari.touristo.core.data.data_model.ProvinceModel
import com.github.hosseinzafari.touristo.core.data.data_model.provinceData
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import com.queezo.app.assets.card_1_2
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 24/06/2023 - 8:28 PM
 * @project Touristo
 */

@HiltViewModel
class AddLocationViewModel @Inject constructor(): XViewModel<AddLocationEffect, AddLocationAction, AddLocationState>() {

    override val processor = processor(
        initialState = AddLocationState(
            province = null,
            category = null,
            name = "" ,
            description = "" ,
            pictureUrl = null ,
            status = XStatus . Idle,
            effects = null,
        ),
        actionReducer = ::reducer
    )

    private fun reducer(oldState: AddLocationState , action: AddLocationAction) {
        when(action) {
            is AddLocationAction.OnChangeProvince ->
                processor.setState(oldState.copy(province = action.newLocation))
            is AddLocationAction.OnChangeDesc ->
                processor.setState(oldState.copy(description = action.newText))
            is AddLocationAction.OnChangeName ->
                processor.setState(oldState.copy(name = action.newText))
            is AddLocationAction.OnChangePicture ->
                processor.setState(oldState.copy(pictureUrl = action.newPicture))
            is AddLocationAction.OnChangeCategory ->
                processor.setState(oldState.copy(category = action.newCategory))

            AddLocationAction.Submit -> {
                processor.setState(oldState.copy(status = XStatus.Loading))
                if (oldState.description.isBlank() || oldState.name.isBlank() ||
                    oldState.pictureUrl == null || oldState.province == null) {

                    processor.setState(oldState.copy(status = XStatus.Error("لطفا تمامی فیلد ها را پر کنید")))
                    return
                }

                viewModelScope.launch {
                    delay(2000L)
                    LocationData.add(LocationModel((Math.random() * 10000 + 80).toInt() ,
                        oldState.description ,
                        card_1_2 ,
                        oldState.name ,
                        oldState.province!! ,
                        0 ,
                        oldState.category!! ,
                        mutableListOf() ,
                        imageUri = oldState.pictureUrl
                    ))

                    processor.setState(oldState.copy(status = XStatus.Idle , effects = AddLocationEffect.NavigateToHome))
                }

            }

        }
    }
}