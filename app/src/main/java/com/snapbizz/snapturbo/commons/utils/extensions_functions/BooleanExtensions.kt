package com.snapbizz.snapturbo.commons.utils.extensions_functions

inline fun Boolean.ifTrue(block: () -> Unit) {
    if (this) block()
}

inline fun Boolean.ifFalse(block: () -> Unit) {
    if (!this) block()
}