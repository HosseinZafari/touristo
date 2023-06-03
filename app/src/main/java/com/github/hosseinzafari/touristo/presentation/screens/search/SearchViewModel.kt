package com.github.hosseinzafari.touristo.presentation.screens.search

import com.github.hosseinzafari.touristo.base.system.mvi.XProcessor
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus
import com.github.hosseinzafari.touristo.presentation.screens.login.XViewModel

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 02/06/2023 - 2:49 PM
 * @project Touristo
 */

class SearchViewModel : XViewModel<SearchEffect , SearchAction , SearchState>() {
    override val processor = processor(
        initialState = SearchState(text = "" ,
            data = listOf() ,
            status = XStatus.Idle ,
            effects = null ,
        ) ,
        actionReducer = ::reducer
    )


    private fun reducer(oldState: SearchState , searchAction: SearchAction ) {
        when(searchAction) {
            is SearchAction.OnSearchChanged -> {
                processor.setState(oldState.copy(text = searchAction.text))
            }

            is SearchAction.Submit -> {
                
            }

             is SearchAction.ClickOnLocationCard -> {

            }

            else -> {}
        }
    }
}