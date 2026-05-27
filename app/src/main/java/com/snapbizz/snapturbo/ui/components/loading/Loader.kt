package com.snapbizz.snapturbo.ui.components.loading

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snapbizz.snapturbo.ui.components.font.RobotoFont
import com.snapbizz.snapturbo.ui.components.text.SnapText
import com.snapbizz.snapturbo.ui.components.text.SnapTextVariant

@Composable
fun CsStyleLoader(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "loader")

    val translateX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 64f,
        animationSpec = infiniteRepeatable(
            animation = tween(3500, easing = FastOutSlowInEasing)
        ),
        label = "translate"
    )

    val widthAnim by infiniteTransition.animateFloat(
        initialValue = 16f,
        targetValue = 80f,
        animationSpec = infiniteRepeatable(
            animation = tween(3500, easing = FastOutSlowInEasing)
        ),
        label = "width"
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SnapText(
            text = "Loading",
            color = Color(0xFF006DA8),
            fontStyle = FontStyle.Normal,
            isBold = true,
            style = RobotoFont.Regular,
            textVariant = SnapTextVariant.HEADING
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .width(80.dp)
                .height(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .offset(x = translateX.dp)
                    .width(widthAnim.dp)
                    .height(16.dp)
                    .background(
                        color = Color(0xFF006DA8),
                        shape = RoundedCornerShape(50)
                    )
            )
        }
    }
}
@Composable
fun PosLoadingBar() {

    val infiniteTransition = rememberInfiniteTransition(label = "pos_gradient")

    val translateX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 300f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "translate"
    )

    Box(
        modifier = Modifier
            .width(280.dp)            // ✅ POS fixed width
            .height(58.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.White, Color(0xFF006DA8)),
                    start = Offset(translateX, 0f),
                    end = Offset(translateX + 200f, 0f)
                )
            )
            .shadow(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "LOADING...",
            fontSize = 18.sp,
            letterSpacing = 3.sp,
            color = Color.White,
            modifier = Modifier.graphicsLayer {
                blendMode = BlendMode.Difference
            },
            style = TextStyle(
                shadow = Shadow(
                    color = Color.White,
                    blurRadius = 2f
                )
            )
        )

    }
}

@Composable
fun PosLoaderWithText(
    text: String
) {
    val infiniteTransition = rememberInfiniteTransition(label = "rotation")

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing)
        ),
        label = "rotate"
    )

    Box(
        modifier = Modifier
            .size(width = 175.dp, height = 80.dp),
        contentAlignment = Alignment.Center
    ) {

        // Cloud background
        Canvas(modifier = Modifier.fillMaxSize()) {
            val white = Color.White

            drawCircle(
                color = white,
                radius = 35.dp.toPx(),
                center = Offset(25.dp.toPx(), 55.dp.toPx())
            )

            drawCircle(
                color = white,
                radius = 60.dp.toPx(),
                center = Offset(87.dp.toPx(), 38.dp.toPx())
            )

            drawCircle(
                color = white,
                radius = 35.dp.toPx(),
                center = Offset(150.dp.toPx(), 55.dp.toPx())
            )

            drawRect(
                color = white,
                topLeft = Offset(25.dp.toPx(), 40.dp.toPx()),
                size = Size(120.dp.toPx(), 40.dp.toPx())
            )
        }

        // TEXT — positioned inside cloud
        SnapText(
            text = text,
            color = Color(0xFF006DA8),
            isBold = true,
            textVariant = SnapTextVariant.DEFAULT,
            modifier = Modifier.offset(y = (-6).dp) // 👈 key fix
        )

        // Spinner — lower inside cloud
        Canvas(
            modifier = Modifier
                .size(36.dp)
                .offset(y = 28.dp)
                .graphicsLayer { rotationZ = rotation }
        ) {
            drawArc(
                color = Color(0xFF006DA8),
                startAngle = 10f,
                sweepAngle = 270f,
                useCenter = false,
                style = Stroke(
                    width = 5.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
    }
}
