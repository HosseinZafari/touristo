package com.github.hosseinzafari.touristo.core

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 21/07/2023 - 6:57 PM
 * @project Touristo
 */

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    var navController: NavHostController? = null
    var splashScreenVisible: Boolean = true

}