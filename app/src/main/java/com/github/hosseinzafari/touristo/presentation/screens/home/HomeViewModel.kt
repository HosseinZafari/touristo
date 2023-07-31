package com.github.hosseinzafari.touristo.presentation.screens.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.data_model.categories
import com.github.hosseinzafari.touristo.presentation.screens.home.data.usecases.GetCategoriesUseCase
import com.github.hosseinzafari.touristo.presentation.screens.home.data.usecases.GetLocationUseCase
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.utils.EmptyContent.status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 08/06/2023 - 9:57 AM
 * @project Touristo
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getLocation: GetLocationUseCase,
    val getCategories: GetCategoriesUseCase,
) : XViewModel<HomeEffect , HomeAction , HomeState>() {
    override val processor = processor(
        initialState = HomeState(
            locationData = listOf() ,
            destinationData = listOf() ,
            categoryData = listOf() ,
            currentCategory = null ,
            status = XStatus.Idle ,
            effects = null ,
        ) ,
        actionReducer = ::reducer
    )

    private fun reducer(oldState: HomeState , action: HomeAction) {
        when(action) {
            is HomeAction.GetData -> {
                processor.setState(oldState.copy(status = XStatus.Loading))
                viewModelScope.launch(Dispatchers.IO)  {

                    try {
                        val locations = getLocation(action.id).first()
                        val categories = getCategories().first()

                        processor.setState(oldState.copy(
                            locationData =  locations,
                            destinationData =  locations ,
                            categoryData = categories ,
                            currentCategory = categories[0],
                            status = XStatus.Idle ,
                        ))

                        Log.i("Test" , "Locations " + locations )
                        Log.i("Test" , "Categories " + categories )

                    } catch (err: Exception) {
                        Log.i("Test" , "Error: " + err )
                    }

                }
            }

            is HomeAction.ChangeCurrentTab -> {
                processor.setState(oldState.copy(currentCategory =  action.category , status = XStatus.Loading))
                viewModelScope.launch(Dispatchers.IO)  {
                    val locations = getLocation(action.category.id).first()
                    processor.setState(oldState.copy(
                        locationData =  locations,
                        destinationData =  locations ,
                        currentCategory =  action.category ,
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