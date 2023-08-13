package com.github.hosseinzafari.touristo.presentation.screens.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.presentation.screens.home.data.usecases.GetBestDestUseCase
import com.github.hosseinzafari.touristo.presentation.screens.home.data.usecases.GetCategoriesUseCase
import com.github.hosseinzafari.touristo.presentation.screens.home.data.usecases.GetLocationUseCase
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.exceptions.HttpRequestException
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
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
    val getBestDests: GetBestDestUseCase,
) : XViewModel<HomeEffect, HomeAction, HomeState>() {
    override val processor = processor(
        initialState = HomeState(
            locationData = listOf(),
            destinationData = listOf(),
            categoryData = listOf(),
            currentCategory = null,
            status = XStatus.Idle,
            effects = null,
        ),
        actionReducer = ::reducer
    )


    private fun reducer(oldState: HomeState, action: HomeAction) {
        when (action) {
            is HomeAction.GetData -> {
                processor.setState(oldState.copy(status = XStatus.Loading))
                val handler = CoroutineExceptionHandler { ctx, trw ->
                    Log.i("Test", "ERRR : " + trw.message)
                    processor.setState(
                        oldState.copy(
                            locationData = listOf(),
                            destinationData = listOf(),
                            categoryData = listOf(),
                            status = XStatus.Error("خطا در دریافت اطلاعات"),
                        )
                    )
                }

                viewModelScope.launch(Dispatchers.IO + handler) {
                    val locationsAsync = async { getLocation(action.id).first() }
                    val categoriesAsync = async { getCategories().first() }
                    val destinationAsync = async { getBestDests().first() }

                    val categories = categoriesAsync.await().sortedBy {
                        it.id
                    }

                    processor.setState(
                        oldState.copy(
                            locationData = locationsAsync.await(),
                            destinationData = destinationAsync.await(),
                            categoryData = categories,
                            currentCategory = categories.filter { it.id == 1 }[0],
                            status = XStatus.Idle,
                        )
                    )
                }

            }

            is HomeAction.ChangeCurrentTab -> {
                processor.setState(
                    oldState.copy(
                        currentCategory = action.category,
                        status = XStatus.Loading
                    )
                )
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val locations = getLocation(action.category.id).first()
                        processor.setState(
                            oldState.copy(
                                locationData = locations,
                                currentCategory = action.category,
                                status = XStatus.Idle,
                            )
                        )
                    } catch (err: Exception) {
                        Log.i("Test", "Error: " + err)
                        processor.setState(
                            oldState.copy(
                                status = XStatus.Error("خطا در دریافت اطلاعات"),
                            )
                        )
                    } catch (err: HttpRequestException) {
                        Log.i("Test", "Error Timeout : " + err)
                        processor.setState(
                            oldState.copy(
                                status = XStatus.Error("خطا در دریافت اتصال به شبکه"),
                            )
                        )
                    }
                }
            }

            is HomeAction.ClickOnCardItem -> {
                processor.setState(oldState.copy(effects = HomeEffect.NavigateToLocationDesc(action.id)))
            }

            is HomeAction.ClickOnAccountButton -> {
                processor.setState(oldState.copy(effects = HomeEffect.NavigateToUserSetting))
            }

            is HomeAction.ClickOnMostDestinationCard -> {
                Log.i("Test", "ClickOnMostDestinationCard ${action.id}")
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