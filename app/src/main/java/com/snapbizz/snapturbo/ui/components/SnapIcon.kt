package com.snapbizz.snapturbo.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.snapbizz.snapturbo.ui.theme.SnapThemeConfig

@Composable
fun SnapIcon(
    iconResId: Int,
    contentDescription: String? = "",
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    tint: Color = SnapThemeConfig.Text75,
    onClick: (() -> Unit)? = null
) {
    val modifierNew = if (onClick != null) {
        modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }) { onClick() }
    } else {
        modifier
    }
    Icon(
        painter = painterResource(iconResId),
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifierNew
    )
}