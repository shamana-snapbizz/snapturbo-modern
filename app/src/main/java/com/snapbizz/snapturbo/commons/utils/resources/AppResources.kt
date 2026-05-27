package com.snapbizz.snapturbo.commons.utils.resources

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


interface AppResources {
    fun getString(@StringRes resId : Int) : String
    fun getString(@StringRes resId: Int,vararg formatArgs : Any) : String
    fun getDrawable(@DrawableRes resId : Int) : Drawable?
}

