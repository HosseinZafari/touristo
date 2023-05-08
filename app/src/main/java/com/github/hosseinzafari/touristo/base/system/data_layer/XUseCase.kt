package com.github.hosseinzafari.touristo.base.system.data_layer

/**
 * @author Hossein Zafari
 * @email ho3einzafari@gmail.com
 * @created 01/05/2023 - 11:22 PM
 * @project Touristo
 */

abstract class XUseCase<D: XDomain> {
    abstract protected val domain: D
}