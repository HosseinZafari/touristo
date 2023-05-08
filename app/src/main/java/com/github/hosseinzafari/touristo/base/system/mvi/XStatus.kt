package com.github.hosseinzafari.touristo.base.system.mvi

import com.github.hosseinzafari.touristo.presentation.screens.login.LoginEffect

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 28/04/2023 - 5:18 PM
 * @project Touristo
 */

/*
* XStatus is a status of a state , Loading , Idle , Error
* this is our status in XProcessor lifecycle
* */
sealed class XStatus {
    object Loading : XStatus()
    object Idle : XStatus()
    data class Error(val msg: String) : XStatus()
}