package com.snapbizz.snapturbo.commons.language

import android.content.Context
import android.content.res.Configuration
import java.util.Locale
import androidx.core.content.edit

object LocaleManager {

    private const val PREF_LANG = "pref_lang"
    private const val DEFAULT_LANG = "en"

    fun setLanguage(context: Context, language: String) {
        context.getSharedPreferences("app_lang", Context.MODE_PRIVATE)
            .edit {
                putString(PREF_LANG, language)
            }
    }

    fun getLanguage(context: Context): String =
        context.getSharedPreferences("app_lang", Context.MODE_PRIVATE)
            .getString(PREF_LANG, DEFAULT_LANG)!!

    fun wrapContext(base: Context): Context {
        val lang = getLanguage(base)
        val locale = Locale.forLanguageTag(lang) // ✅ recommended
        Locale.setDefault(locale)

        val config = Configuration(base.resources.configuration)
        config.setLocale(locale) // ✅ not deprecated

        return base.createConfigurationContext(config)
    }
}

