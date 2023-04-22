package com.example.bluetoothdetector.common.repository

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.core.os.LocaleListCompat
import com.example.bluetoothdetector.common.domain.English
import com.example.bluetoothdetector.common.domain.Language
import com.example.bluetoothdetector.common.domain.Languages
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

// Manages all operations related to languages
@Singleton
class LanguageRepository @Inject constructor(
    val context: Context,
) {
    companion object {
        val AvailableLanguages: List<Language> = Languages
        val DefaultLanguage = English
    }

    // Retrieves the current application locale (from device internal settings)
    lateinit var getLocale: () -> LocaleListCompat

    // Change the current application locale (in device internal settings)
    lateinit var changeLocale: (Locale) -> Unit

    // Holds the current selected language
    lateinit var currentLanguage: MutableState<Language>

    // Retrieves the current Language from specified locale
    fun getLanguageFromLocale(
        locale: Locale = getLocale()[0] ?: DefaultLanguage.locale
    ): Language {
        return AvailableLanguages.find { it.locale == locale } ?: DefaultLanguage
    }

    // Changes the current language
    fun changeLanguage(language: Language) {
        changeLocale(language.locale)
        currentLanguage.value = language
    }
}