package com.github.hosseinzafari.touristo.presentation.screens.search

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.dto.provinceData
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 02/06/2023 - 2:49 PM
 * @project Touristo
 */

class SearchViewModel : XViewModel<SearchEffect, SearchAction, SearchState>() {
    override val processor = processor(
        initialState = SearchState(
            text = "",
            data = listOf(),
            selectedProvince = null,
            status = XStatus.Idle,
            effects = null,
        ),
        actionReducer = ::reducer
    )


    private fun reducer(oldState: SearchState, action: SearchAction) {
        when (action) {
            is SearchAction.OnSearchChanged -> {
                processor.setState(oldState.copy(text = action.text))
            }


            is SearchAction.Submit -> {
                processor.setState(
                    oldState.copy(
                        status = XStatus.Loading
                    )
                )

                viewModelScope.launch {
                    delay(2000L)
                   /* processor.setState(oldState.copy(
                        data = LocationData.filter {
                            it.name.contains(oldState.text) || it.location.name.contains(oldState.text)
                        } ,
                        status = XStatus.Idle
                    ))*/
                }
            }

            is SearchAction.GetSelectedProvince -> {
                Log.i("Test"  , "GetSelectedProvince ${action.provinceID}")
                processor.setState(
                    oldState.copy(
                        status = XStatus.Loading
                    )
                )

                viewModelScope.launch {
                    processor.setState(
                        oldState.copy(
                            selectedProvince = provinceData.filter {
                                action.provinceID == it.id
                            }.firstOrNull() ,
                            status = XStatus.Idle
                        )
                    )
                }
            }

            is SearchAction.ClickOnLocationCard -> {
                processor.setState(
                    oldState.copy(
                        effects = SearchEffect.NavigateToLocationCardItem(action.locationID)
                    )
                )
            }

        }
    }
}