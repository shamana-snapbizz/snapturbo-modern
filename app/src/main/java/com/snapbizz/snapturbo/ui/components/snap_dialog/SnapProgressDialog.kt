package com.snapbizz.snapturbo.ui.components.snap_dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.snapbizz.snapturbo.ui.theme.Dimens
import kotlinx.coroutines.delay

/*
@Composable
fun SnapProgressDialog(isVisible: Boolean=true,
                       message: String? = null,
                       onCancel: (() -> Unit)? = null,
                       autoTimeoutMillis: Long? = null,
                       modifier: Modifier = Modifier,
                       backgroundColor: Color = Color.Black.copy(0.3f),
                       dialogBackgroundColor: Color = SnapThemeConfig.SurfaceBg,
                       indicatorColor: Color = SnapThemeConfig.Primary,
                       dialogCornerRadius: Dp = Dimens.cornerMedium,
                       indicatorSize: Dp = Dimens.progressIndicatorSizeMedium,
                       dialogWidth: Dp = Dimens.size_180
) {
    var show by remember { mutableStateOf(isVisible) }

    LaunchedEffect(key1 = show, key2 = autoTimeoutMillis) {
        if (show && autoTimeoutMillis != null) {
            delay(autoTimeoutMillis)
            show = false
            onCancel?.invoke()
        }
    }

    if (show) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable{}
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = modifier
                    .width(dialogWidth)
                    .background(dialogBackgroundColor, RoundedCornerShape(dialogCornerRadius))
                    .padding(Dimens.paddingMedium),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimens.paddingSmallMedium)
            ) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_light))
                val progress by animateLottieCompositionAsState(
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )
                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                    modifier = Modifier.size(indicatorSize * 2)
                )

                if (!message.isNullOrEmpty()) {
                    SnapText(
                        text = message,
                        fontSize = SnapTextComponent.subheadingFontSize,
                        fontWeight = SnapTextComponent.defaultFontWeight,
                        textAlign = TextAlign.Center
                    )
                }

                if (onCancel != null) {
                    SnapText(
                        text = "Cancel",
                        fontSize = SnapTextComponent.defaultFontSize,
                        fontWeight = SnapTextComponent.defaultFontWeight,
                        color = SnapThemeConfig.Primary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .clickable {
                                show = false
                                onCancel()
                            }
                            .padding(vertical = Dimens.paddingTooSmall)
                    )
                }
            }
        }
    }
}*/
