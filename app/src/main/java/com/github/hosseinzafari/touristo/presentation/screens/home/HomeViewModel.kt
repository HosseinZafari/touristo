package com.github.hosseinzafari.touristo.presentation.screens.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.data_model.LocationData
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 08/06/2023 - 9:57 AM
 * @project Touristo
 */

@HiltViewModel
class HomeViewModel @Inject constructor() : XViewModel<HomeEffect , HomeAction , HomeState>() {
    override val processor = processor(
        initialState = HomeState(
            locationData = listOf() ,
            destinationData = listOf() ,
            status = XStatus.Idle ,
            effects = null ,
        ) ,
        actionReducer = ::reducer
    )

    private fun reducer(oldState: HomeState , action: HomeAction) {
        when(action) {
            is HomeAction.GetData -> {
                processor.setState(oldState.copy(status = XStatus.Loading))
                viewModelScope.launch  {
                    delay(2000L) // simulate for loading
                    processor.setState(oldState.copy(
                        locationData = LocationData.filter {
                           it.categoryModel.id == action.id
                        } ,
                        destinationData =  LocationData.subList(1 , 3) ,
                        status = XStatus.Idle ,
                    ))
                }
            }

            is HomeAction.ChangeCurrentTab -> {
                processor.setState(oldState.copy(status = XStatus.Loading))
                viewModelScope.launch  {
                    delay(2000L) // simulate for loading
                    processor.setState(oldState.copy(
                        locationData = LocationData.filter {
                            it.categoryModel.id == action.id
                        } ,
                        status = XStatus.Idle ,
                    ))
                }
            }

            is HomeAction.ClickOnCardItem -> {
                processor.setState(oldState.copy(effects = HomeEffect.NavigateToLocationDesc(action.id)))
            }

            is HomeAction.ClickOnBookmarkButton -> {
                processor.setState(oldState.copy(effects = HomeEffect.NavigateToBookmark))
            }

            is HomeAction.ClickOnMostDestinationCard -> {
                Log.i("Test" , "ClickOnMostDestinationCard ${action.id}")
                processor.setState(oldState.copy(effects = HomeEffect.NavigateToSearch(action.id)))
            }

            is HomeAction.ClickOnSearchButton -> {
                processor.setState(oldState.copy(effects = HomeEffect.NavigateToSearch(action.id)))
            }
            HomeAction.ClickOnFloatingActionButton -> {
                processor.setState(oldState.copy(effects = HomeEffect.NavigateToAddLocation))
            }
        }
    }


}