package com.github.hosseinzafari.touristo.presentation.screens.login

import androidx.lifecycle.ViewModel
import com.github.hosseinzafari.touristo.base.system.mvi.XAction
import com.github.hosseinzafari.touristo.base.system.mvi.XEffect
import com.github.hosseinzafari.touristo.base.system.mvi.XProcessor
import com.github.hosseinzafari.touristo.base.system.mvi.XState

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 27/04/2023 - 12:55 PM
 * @project Touristo
 */

/*
* XViewModel is a Base viewModel class for insert some custom functions
* For our framework based on MVI arch (like redux nature)
* We using XProcessor for holding our main concept MVI bi-directional arch
* */
abstract class XViewModel< E : XEffect, A : XAction , S : XState<E>> : ViewModel() {

    abstract val processor: XProcessor<E, A, S>

    protected fun processor(
        initialState: S,
        actionReducer: (S, A) -> Unit,
    ) = object : XProcessor<E,A,S>(initialState) {
        override val action = actionReducer
    }
}


