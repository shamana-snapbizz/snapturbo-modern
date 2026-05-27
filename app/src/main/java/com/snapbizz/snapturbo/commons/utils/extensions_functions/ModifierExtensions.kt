package com.snapbizz.snapturbo.commons.utils.extensions_functions

import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.clickableOnce(
    enabled: Boolean = true,
    onClick: () -> Unit
): Modifier = composed {
    var clicked by remember { mutableStateOf(false) }

    Modifier.clickable(enabled = enabled && !clicked) {
        clicked = true
        onClick()
    }
}