package com.snapbizz.snapturbo.ui.components.text

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

object SnapTextComponent {
        val smallFontSize: TextUnit = 12.sp

        val defaultFontSize : TextUnit = 14.sp
        val subheadingFontSize: TextUnit = 20.sp
        val headingFontSize : TextUnit = 32.sp
        val normalHeading : TextUnit = 16.sp
        val defaultFontWeight : FontWeight = FontWeight.Companion.Normal
        val headingFontWeight: FontWeight = FontWeight.Companion.Bold
        val subheadingFontWeight: FontWeight = FontWeight.Companion.SemiBold

        val defaultTextAlign: TextAlign = TextAlign.Companion.Start
        val centerTextAlign: TextAlign = TextAlign.Companion.Center
        val endTextAlign: TextAlign = TextAlign.Companion.End
}