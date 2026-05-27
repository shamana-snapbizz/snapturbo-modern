package com.snapbizz.snapturbo.ui.components.text

import androidx.compose.ui.text.TextStyle
import com.snapbizz.snapturbo.ui.components.font.snapFontFamily

object SnapTextStyle {
        val default = TextStyle(
            fontSize = SnapTextComponent.defaultFontSize,
            fontWeight = SnapTextComponent.defaultFontWeight,
            fontFamily = snapFontFamily
        )

        val heading = TextStyle(
            fontSize = SnapTextComponent.headingFontSize,
            fontWeight = SnapTextComponent.headingFontWeight,
            fontFamily = snapFontFamily
        )

        val subheading = TextStyle(
            fontSize = SnapTextComponent.subheadingFontSize,
            fontWeight = SnapTextComponent.subheadingFontWeight,
            fontFamily = snapFontFamily
        )

        val label = TextStyle(
            fontSize = SnapTextComponent.defaultFontSize,
            fontWeight = SnapTextComponent.defaultFontWeight,
            fontFamily = snapFontFamily
        )

        val small = TextStyle(
            fontSize = SnapTextComponent.smallFontSize,
            fontWeight = SnapTextComponent.defaultFontWeight,
            fontFamily = snapFontFamily
        )
}

/*How You USE This in Screens
SnapText(
    text = "Dashboard",
    variant = SnapTextVariant.HEADING
)

SnapText(
    text = "Welcome back",
    variant = SnapTextVariant.SUBHEADING
)

SnapTextWithLabel(
    label = "Store:",
    value = "Snap Mart"
)

SnapHighlightText(
    text = "Offer 50% OFF",
    highlightRange = 6..9
)*/