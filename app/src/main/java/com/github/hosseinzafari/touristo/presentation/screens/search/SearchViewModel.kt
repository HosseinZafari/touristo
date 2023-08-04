package com.github.hosseinzafari.touristo.presentation.screens.search

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.dto.provinceData
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import com.github.hosseinzafari.touristo.presentation.screens.search.data.usecases.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 02/06/2023 - 2:49 PM
 * @project Touristo
 */

@HiltViewModel
class SearchViewModel @Inject constructor(
    val search: SearchUseCase ,
) : XViewModel<SearchEffect, SearchAction, SearchState>() {
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

                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val locations = search(oldState.text).first()
                        processor.setState(
                            oldState.copy(
                                data = locations ,
                                status = XStatus.Idle
                            )
                        )
                    } catch (err: Exception) {
                        processor.setState(
                            oldState.copy(
                                status = XStatus.Error("مشکل در برقراری ارتباط")
                            )
                        )
                        Log.i("Test" , "Error in search Err: " + err )
                    }
                }
            }

            is SearchAction.GetSelectedProvince -> {
                Log.i("Test", "GetSelectedProvince ${action.provinceID}")
                processor.setState(
                    oldState.copy(
                        status = XStatus.Loading
                    )
                )

                viewModelScope.launch(Dispatchers.IO) {
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