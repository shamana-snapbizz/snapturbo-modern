package com.snapbizz.snapturbo.ui.components

import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SnapCheckBox(checked: Boolean,
                 onCheckedChange: (Boolean) -> Unit,
                 modifier: Modifier = Modifier,
                 enabled: Boolean = true
) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled
    )
}