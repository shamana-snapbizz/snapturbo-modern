package com.snapbizz.snapturbo.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.snapbizz.snapturbo.ui.theme.Dimens
import com.snapbizz.snapturbo.ui.theme.SnapThemeConfig

@Composable
fun SnapImage(
    imageResId: Int,
    contentDescription: String? = "",
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    color: Color = SnapThemeConfig.Text75,
    onClick: (() -> Unit)? = null,
    padding: Dp = Dimens.paddingSmall
) {
    val interactionSource = remember { MutableInteractionSource() }

    val finalModifier = modifier
        .then(
            if (onClick != null) {
                Modifier.clickable(
                    interactionSource = interactionSource, indication = null, onClick = onClick
                )
            } else Modifier
        )
        .padding(padding)

    Image(
        painter = painterResource(id = imageResId),
        contentDescription = contentDescription,
        modifier = finalModifier,
        colorFilter = remember(color) { ColorFilter.tint(color) })
}