package com.snapbizz.snapturbo.onboarding.login.presentation.viewmodel

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import com.snapbizz.snapturbo.commons.language.LocaleManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    @ApplicationContext private var context: Context
) : ViewModel() {

    private val _language =
        MutableStateFlow(LocaleManager.getLanguage(context))
    val language: StateFlow<String> = _language

    fun changeLanguage(lang: String) {
        LocaleManager.setLanguage(context, lang)
        _language.value = lang // 🔥 THIS triggers recomposition
    }
}
