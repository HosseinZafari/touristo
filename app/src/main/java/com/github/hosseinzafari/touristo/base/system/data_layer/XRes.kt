package com.github.hosseinzafari.touristo.base.system.data_layer

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 20/07/2023 - 3:11 PM
 * @project Touristo
 */

data class XRes<T>(
    val data: T? = null ,
    val error: Boolean = false,
    val exception: Exception? = null,
)
