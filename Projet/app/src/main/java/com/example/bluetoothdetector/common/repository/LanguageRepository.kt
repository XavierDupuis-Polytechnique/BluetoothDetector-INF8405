package com.example.bluetoothdetector.common.repository

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.os.LocaleListCompat
import com.example.bluetoothdetector.common.domain.English
import com.example.bluetoothdetector.common.domain.Language
import com.example.bluetoothdetector.common.domain.Languages
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageRepository @Inject constructor(
    val context: Context,
) {
    companion object {
        val AvailableLanguages: List<Language> = Languages
        val DefaultLanguage = English
    }

    lateinit var getLocale: () -> LocaleListCompat
    lateinit var changeLocale: (Locale) -> Unit
    lateinit var currentLanguage: MutableState<Language>

    fun getLanguageFromLocale(
        locale: Locale = getLocale()[0] ?: DefaultLanguage.locale
    ): Language {
        return AvailableLanguages.find { it.locale == locale } ?: DefaultLanguage
    }

    fun changeLanguage(language: Language) {
        changeLocale(language.locale)
        currentLanguage.value = language
    }
}