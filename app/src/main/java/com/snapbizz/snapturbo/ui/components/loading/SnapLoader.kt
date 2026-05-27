package com.snapbizz.snapturbo.ui.components.loading

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.snapbizz.snapturbo.ui.components.text.SnapText
import com.snapbizz.snapturbo.ui.theme.SnapTextEmphasis
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.graphics.Color
import com.snapbizz.snapturbo.ui.theme.SnapThemeConfig

@Composable
fun SnapProgressLoader(
    progress: Int, // 0–100
    modifier: Modifier = Modifier,
    showPercentage: Boolean = true,
    color: Color = SnapThemeConfig.Primary
) {
    // Clamp to avoid crashes or bad UI
    val safeProgress = progress.coerceIn(0, 100)
    val progressFraction = safeProgress / 100f

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        CircularProgressIndicator(
            progress = { progressFraction }, // correct Material 3 API
            color = color
        )

        if (showPercentage) {
            Spacer(Modifier.height(8.dp))
            SnapText(
                text = "$safeProgress%",
                emphasis = SnapTextEmphasis.SECONDARY
            )
        }
    }
}