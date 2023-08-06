package com.github.hosseinzafari.touristo.presentation.screens.home

import com.github.hosseinzafari.touristo.base.system.mvi.XAction
import com.github.hosseinzafari.touristo.base.system.mvi.XEffect
import com.github.hosseinzafari.touristo.base.system.mvi.XState
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.core.data.data_model.BestDestination
import com.github.hosseinzafari.touristo.core.data.data_model.Category
import com.github.hosseinzafari.touristo.core.data.data_model.Location
import com.github.hosseinzafari.touristo.core.data.dto.LocationModel

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 08/06/2023 - 9:58 AM
 * @project Touristo
 */
 
data class HomeState(
    var locationData: List<Location>,
    var destinationData: List<BestDestination>,
    var categoryData: List<Category>,
    var currentCategory: Category? ,
    override var status: XStatus,
    override val effects: HomeEffect?
): XState<HomeEffect>


sealed class HomeEffect : XEffect {
    data class NavigateToSearch(
        val locationID: Int
    ) : HomeEffect()

    data class  NavigateToLocationDesc(
        val locationID: Int
    ): HomeEffect()


    object  NavigateToBookmark : HomeEffect()
    object  NavigateToAddLocation : HomeEffect()

}

sealed class HomeAction: XAction {
    data class ClickOnCardItem(
        val id: Int
    ): HomeAction()

    data class ClickOnMostDestinationCard(
        val id: Int
    ) : HomeAction()

    object ClickOnBookmarkButton : HomeAction()
    object ClickOnFloatingActionButton : HomeAction()

    data class ClickOnSearchButton (
        val id: Int
    ) : HomeAction()

    data class GetData(val id: Int) : HomeAction()

    data class ChangeCurrentTab(val category: Category) : HomeAction()


}

