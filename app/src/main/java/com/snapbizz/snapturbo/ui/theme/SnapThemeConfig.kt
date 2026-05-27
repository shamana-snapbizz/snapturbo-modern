package com.snapbizz.snapturbo.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object SnapThemeConfig {

    var theme: ThemeConfig = ThemeConfig.default()
        private set

    /* -----------------------------
     * Text alpha values
     * ----------------------------- */
    private const val ALPHA_PRIMARY = 1.0f
    private const val ALPHA_SECONDARY = 0.7f
    private const val ALPHA_TERTIARY = 0.5f
    private const val ALPHA_DISABLED = 0.38f

    /* -----------------------------
     * Brand / base colors
     * ----------------------------- */

    val Primary: Color
        @Composable get() = theme.primary

    val Secondary: Color
        @Composable get() = theme.secondary

    val Background: Color
        @Composable get() = theme.containerBg

    val Surface: Color
        @Composable get() = theme.surfaceBg

    val ContainerBg: Color
        @Composable get() = theme.containerBg

    val Error: Color
        @Composable get() = theme.error

    val Success: Color
        @Composable get() = theme.success

    /* -----------------------------
     * Text colors (semantic emphasis)
     * ----------------------------- */

    @Composable
    fun textColor(emphasis: SnapTextEmphasis): Color {
        val base = theme.textColor
        return when (emphasis) {
            SnapTextEmphasis.PRIMARY ->
                base.copy(alpha = ALPHA_PRIMARY)

            SnapTextEmphasis.SECONDARY ->
                base.copy(alpha = ALPHA_SECONDARY)

            SnapTextEmphasis.TERTIARY ->
                base.copy(alpha = ALPHA_TERTIARY)

            SnapTextEmphasis.DISABLED ->
                base.copy(alpha = ALPHA_DISABLED)
        }
    }

    /* -----------------------------
     * Backward-compatible aliases
     * ----------------------------- */

    val Text: Color
        @Composable get() = textColor(SnapTextEmphasis.PRIMARY)

    val Text75: Color
        @Composable get() = textColor(SnapTextEmphasis.SECONDARY)

    val Text50: Color
        @Composable get() = textColor(SnapTextEmphasis.TERTIARY)

    val Hint: Color
        @Composable get() = textColor(SnapTextEmphasis.DISABLED)

    /* -----------------------------
     * UI helpers
     * ----------------------------- */

    val Border: Color
        @Composable get() = theme.secondarySurfaceBg.copy(alpha = 0.12f)
    val OtpBox: Color
        @Composable get() = theme.secondarySurfaceBgBlue.copy(alpha = 0.12f)

    val Disabled: Color
        @Composable get() = theme.secondarySurfaceBg.copy(alpha = ALPHA_DISABLED)
}

enum class SnapTextEmphasis {
    PRIMARY,
    SECONDARY,
    TERTIARY,
    DISABLED
}

/*ui/theme/
│
├── ThemeConfig.kt        ← pure data (NO Compose)
├── SnapThemeConfig.kt    ← Compose accessors
├── SnapTheme.kt          ← optional Material bridge

HOW YOU USE THIS IN UI
SnapText(
    text = "Secondary info",
    emphasis = SnapTextEmphasis.SECONDARY
)

*/
