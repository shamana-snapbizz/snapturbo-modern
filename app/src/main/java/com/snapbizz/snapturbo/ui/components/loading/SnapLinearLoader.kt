package com.snapbizz.snapturbo.ui.components.loading

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.snapbizz.snapturbo.ui.theme.SnapThemeConfig

@Composable
fun SnapLinearLoader(
    modifier: Modifier = Modifier,
    color: Color = SnapThemeConfig.Primary
) {
    LinearProgressIndicator(
        modifier = modifier.fillMaxWidth(),
        color = color
    )
}
