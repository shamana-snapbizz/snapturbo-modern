package com.snapbizz.snapturbo.commons.language

import androidx.compose.runtime.staticCompositionLocalOf
import com.snapbizz.snapturbo.commons.utils.resources.AppResources


val LocalAppResources = staticCompositionLocalOf<AppResources> {
    error("AppResources not provided")
}