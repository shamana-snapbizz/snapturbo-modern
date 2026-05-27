package com.snapbizz.snapturbo.ui.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import com.snapbizz.snapturbo.ui.theme.SnapThemeConfig

@Composable
fun SnapHighlightText(
    text: String,
    modifier: Modifier = Modifier,
    variant: SnapTextVariant = SnapTextVariant.DEFAULT,
    highlightRange: IntRange?,
    highlightColor: androidx.compose.ui.graphics.Color = SnapThemeConfig.Primary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    align: TextAlign = TextAlign.Center
) {
    val baseStyle = when (variant) {
        SnapTextVariant.DEFAULT -> SnapTextStyle.default
        SnapTextVariant.HEADING -> SnapTextStyle.heading
        SnapTextVariant.SUBHEADING -> SnapTextStyle.subheading
        SnapTextVariant.LABEL -> SnapTextStyle.label
        SnapTextVariant.SMALL -> SnapTextStyle.small
    }

    val annotatedText = remember(text, highlightRange) {
        if (
            highlightRange != null &&
            highlightRange.first in text.indices &&
            highlightRange.last in text.indices
        ) {
            buildAnnotatedString {
                append(text.substring(0, highlightRange.first))
                withStyle(SpanStyle(color = highlightColor)) {
                    append(text.substring(highlightRange))
                }
                if (highlightRange.last + 1 < text.length) {
                    append(text.substring(highlightRange.last + 1))
                }
            }
        } else {
            AnnotatedString(text)
        }
    }

    Text(
        text = annotatedText,
        modifier = modifier,
        maxLines = maxLines,
        overflow = overflow,
        style = baseStyle,
        textAlign = align
    )
}