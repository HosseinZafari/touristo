package com.github.hosseinzafari.touristo.base.system.mvi

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 28/04/2023 - 4:41 PM
 * @project Touristo
 */


/*
* XState is a interface for our whole State in a screen.
* This defined , because our State should organized and this is a super abstract RULES for
* Our States in a screen , States used in compositions of composable functions
* */
interface XState<E : XEffect> {
    var status: XStatus
    val effects: E?
}
