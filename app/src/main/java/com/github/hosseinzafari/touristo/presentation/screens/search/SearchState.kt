package com.github.hosseinzafari.touristo.presentation.screens.search

import com.github.hosseinzafari.touristo.base.system.mvi.XState
import com.github.hosseinzafari.touristo.base.system.mvi.XStatus

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 02/06/2023 - 2:51 PM
 * @project Touristo
 */

data class SearchState(
    val text: String ,
    val data: List<String> ,
    override var status: XStatus,
    override val effects: SearchEffect?
) : XState<SearchEffect>