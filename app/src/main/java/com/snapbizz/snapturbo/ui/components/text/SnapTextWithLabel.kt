package com.snapbizz.snapturbo.ui.components.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun SnapTextWithLabel(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    spacing: Int = 8,
    valueMaxLines: Int = 1
) {
    Row(modifier = modifier) {
        SnapText(
            text = label,
            textVariant = SnapTextVariant.LABEL
        )
        Spacer(modifier = Modifier.width(spacing.dp))
        SnapText(
            text = value,
            textVariant = SnapTextVariant.DEFAULT,
            maxLines = valueMaxLines,
            overflow = TextOverflow.Ellipsis
        )
    }
}