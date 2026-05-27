package com.snapbizz.snapturbo.ui.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.snapbizz.snapturbo.ui.components.font.snapFontFamily
import com.snapbizz.snapturbo.ui.theme.SnapTextEmphasis
import com.snapbizz.snapturbo.ui.theme.SnapThemeConfig


@Composable
fun SnapText(
    text: String,
    modifier: Modifier = Modifier,
    textVariant: SnapTextVariant = SnapTextVariant.DEFAULT,
    color: androidx.compose.ui.graphics.Color? = null,
    textAlign: TextAlign = SnapTextComponent.defaultTextAlign,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Clip,
    fontStyle: FontStyle = FontStyle.Normal,
    decoration: TextDecoration? = null,
    emphasis: SnapTextEmphasis = SnapTextEmphasis.PRIMARY,
    isBold: Boolean=false,
    style: TextStyle = TextStyle.Default,
) {
    val style: TextStyle = remember(textVariant) {
        when (textVariant) {
            SnapTextVariant.DEFAULT -> SnapTextStyle.default
            SnapTextVariant.HEADING -> SnapTextStyle.heading
            SnapTextVariant.SUBHEADING -> SnapTextStyle.subheading
            SnapTextVariant.LABEL -> SnapTextStyle.label
            SnapTextVariant.SMALL -> SnapTextStyle.small
        }
    }

    val resolvedColor = color ?: SnapThemeConfig.textColor(emphasis)

    Text(
        text = text,
        modifier = modifier,
        maxLines = maxLines,
        minLines = minLines,
        overflow = overflow,
        style = style.copy(
            color = resolvedColor,
            textAlign = textAlign,
            fontStyle = fontStyle,
            textDecoration = decoration,
            fontFamily = snapFontFamily
        )
    )
}

enum class SnapTextVariant {
    DEFAULT,
    HEADING,
    SUBHEADING,
    LABEL,
    SMALL
}



/*Column(Modifier.padding(16.dp)) {

    SnapText(
        text = "Profile",
        variant = SnapTextVariant.HEADING
    )

    SnapText(
        text = "Last updated yesterday",
        emphasis = SnapTextEmphasis.SECONDARY
    )

    Spacer(Modifier.height(12.dp))

    SnapText(
        text = "Email",
        variant = SnapTextVariant.LABEL,
        emphasis = SnapTextEmphasis.TERTIARY
    )

    SnapText(
        text = "user@email.com"
    )

    Spacer(Modifier.height(12.dp))

    SnapText(
        text = "Editing disabled",
        emphasis = SnapTextEmphasis.DISABLED
    )
}*/