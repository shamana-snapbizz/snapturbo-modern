package com.snapbizz.snapturbo.ui.components.loading

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.snapbizz.snapturbo.ui.theme.SnapThemeConfig

@Composable
fun SnapBrandedCircularLoader(
    @DrawableRes logoRes: Int,
    modifier: Modifier = Modifier,
    size: Dp = 64.dp
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(size)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize(),
            strokeWidth = 4.dp,
            color = SnapThemeConfig.Primary
        )

        Image(
            painter = painterResource(id = logoRes),
            contentDescription = "Loading",
            modifier = Modifier.size(size / 2)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    SnapBrandedCircularLoader(logoRes = com.snapbizz.snapturbo.R.drawable.turbo_logo)
}