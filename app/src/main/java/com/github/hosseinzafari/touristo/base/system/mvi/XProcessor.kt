package com.github.hosseinzafari.touristo.base.system.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 28/04/2023 - 4:40 PM
 * @project Touristo
 */

/*
* XProcessor is main or brain in MVI arch our framework ,
* this handle effect , action , and hold our states in under hood ,
* so make our life easier , and just send your action by using : [@code sendAction(mAction: A)]
* and set new state to it
* */
abstract class XProcessor<E : XEffect, A : XAction, S : XState<E>>(initialVal: S) {
    protected var _state: MutableStateFlow<S> = MutableStateFlow(initialVal)
    val subscriberState: StateFlow<S>
        get() = _state
    abstract val action: (S, A) -> Unit


    fun setState(mState: S) {
        _state.tryEmit(mState)
    }

    fun sendAction(mAction: A) {
        action(subscriberState.value, mAction)
    }


    @Composable
    fun SubscribeEffect(
        state: S,
        statusBlock: suspend (XStatus) -> Unit,
        effectsBlock: suspend (E) -> Unit,
    ) {
        LaunchedEffect(key1 = state.status) {
            statusBlock(state.status)
        }

        LaunchedEffect(key1 = state.effects) {
            state.effects?.let{
                effectsBlock(it)
            }
        }
    }
}
