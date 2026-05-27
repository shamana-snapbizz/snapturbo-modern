package com.snapbizz.snapturbo.ui.theme


import androidx.compose.ui.graphics.Color

/**
 * Pure theme configuration.
 * - NO @Composable
 * - NO MaterialTheme
 * - Can come from API / JSON / DB
 */
data class ThemeConfig(
    val primary: Color,
    val onPrimary: Color,

    val secondary: Color,
    val onSecondary: Color,

    val tertiary: Color,
    val onTertiary: Color,

    val textColor: Color,
    val hintColor: Color,

    val textSizeTitle: Int,
    val textSizeBody: Int,

    val containerBg: Color,
    val surfaceBg: Color,
    val secondarySurfaceBg: Color,
    val secondarySurfaceBgBlue: Color,

    val success: Color,
    val error: Color,
) {
    companion object {
        fun default(): ThemeConfig = ThemeConfig(
            primary = SnapColor.primaryColor,
            onPrimary = Color.White,
            secondary = Color.Black,
            onSecondary = Color.White,

            tertiary = Color(0xFF03DAC6),
            onTertiary = Color.Black,

            textColor = Color.Black,
            hintColor = Color.Black.copy(alpha = 0.38f),

            textSizeTitle = 16,
            textSizeBody = 14,

            containerBg = Color.White,
            surfaceBg = Color(0xFFE6E6E6),
            secondarySurfaceBg = Color(0xFF808080),
            secondarySurfaceBgBlue = Color(0xFF006DA8),
            success = Color(0xFF006600),
            error = Color(0xFF990000)
        )
    }
}
