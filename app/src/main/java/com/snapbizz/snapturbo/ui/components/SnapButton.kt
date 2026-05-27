package com.snapbizz.snapturbo.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.snapbizz.snapturbo.ui.components.text.SnapText
import com.snapbizz.snapturbo.ui.components.text.SnapTextVariant
import com.snapbizz.snapturbo.ui.theme.SnapTextEmphasis
import com.snapbizz.snapturbo.ui.theme.SnapThemeConfig

@Composable
fun SnapButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: SnapButtonVariant = SnapButtonVariant.PRIMARY,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    backgroundColorInt: Int? = null,
    textColorInt: Int? = null
) {

    // ---------------- COLORS ----------------

    val backgroundColor: Color? = backgroundColorInt?.let { Color(it) }
    val textColorOverride: Color? = textColorInt?.let { Color(it) }

    val colors = when (variant) {
        SnapButtonVariant.PRIMARY ->
            ButtonDefaults.buttonColors(
                containerColor = backgroundColor ?: SnapThemeConfig.Primary,
                disabledContainerColor = (backgroundColor ?: SnapThemeConfig.Primary)
                    .copy(alpha = 0.38f)
            )

        SnapButtonVariant.SECONDARY ->
            ButtonDefaults.buttonColors(
                containerColor = backgroundColor ?: SnapThemeConfig.Secondary,
                disabledContainerColor = (backgroundColor ?: SnapThemeConfig.Secondary)
                    .copy(alpha = 0.38f)
            )

        SnapButtonVariant.OUTLINE ->
            ButtonDefaults.outlinedButtonColors()

        SnapButtonVariant.TEXT ->
            ButtonDefaults.textButtonColors()
    }

    val textVariant = textVariantForButton(variant)

    val textColor = textColorOverride ?: textColorForButton(
        variant = variant,
        enabled = enabled
    )

    // ---------------- CONTENT ----------------

    val content: @Composable RowScope.() -> Unit = {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(18.dp),
                strokeWidth = 2.dp,
                color = textColor
            )
        } else {
            leadingIcon?.invoke()
            if (leadingIcon != null) Spacer(Modifier.width(8.dp))

            SnapText(
                text = text,
                textVariant = textVariant,
                color = textColor
            )
        }
    }

    // ---------------- BUTTON TYPES ----------------

    when (variant) {
        SnapButtonVariant.PRIMARY,
        SnapButtonVariant.SECONDARY ->
            Button(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled && !isLoading,
                colors = colors,
                content = content
            )

        SnapButtonVariant.OUTLINE ->
            OutlinedButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled && !isLoading,
                content = content
            )

        SnapButtonVariant.TEXT ->
            TextButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled && !isLoading,
                content = content
            )
    }
}


enum class SnapButtonVariant {
    PRIMARY,
    SECONDARY,
    OUTLINE,
    TEXT
}


private fun textVariantForButton(
    variant: SnapButtonVariant
): SnapTextVariant =
    when (variant) {
        SnapButtonVariant.PRIMARY,
        SnapButtonVariant.SECONDARY,
        SnapButtonVariant.OUTLINE,
        SnapButtonVariant.TEXT -> SnapTextVariant.DEFAULT
    }

@Composable
private fun textColorForButton(
    variant: SnapButtonVariant,
    enabled: Boolean
): Color {
    if (!enabled) {
        return SnapThemeConfig.textColor(SnapTextEmphasis.DISABLED)
    }

    return when (variant) {
        SnapButtonVariant.PRIMARY -> SnapThemeConfig.theme.onPrimary
        SnapButtonVariant.SECONDARY -> SnapThemeConfig.theme.onSecondary
        SnapButtonVariant.OUTLINE,
        SnapButtonVariant.TEXT -> SnapThemeConfig.Primary
    }
}
