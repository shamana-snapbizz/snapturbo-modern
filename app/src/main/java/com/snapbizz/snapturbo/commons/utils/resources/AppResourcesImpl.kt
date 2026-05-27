package com.snapbizz.snapturbo.commons.utils.resources

import android.content.Context
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppResourcesImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AppResources {

    override fun getString(@StringRes resId: Int): String =
        context.getString(resId)

    override fun getString(
        @StringRes resId: Int,
        vararg formatArgs: Any
    ): String =
        context.getString(resId, *formatArgs)

    override fun getDrawable(@DrawableRes resId: Int): Drawable? =
        ContextCompat.getDrawable(context, resId)

    /** 🔥 Creates a NEW AppResources with localized Context */
    fun withLanguage(lang: String): AppResources {
        val locale = Locale.forLanguageTag(lang)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)

        val localizedContext =
            context.createConfigurationContext(config)

        return AppResourcesImpl(localizedContext)
    }
}
