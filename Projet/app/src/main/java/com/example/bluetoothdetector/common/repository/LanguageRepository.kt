package com.example.bluetoothdetector.common.repository

import android.content.Context
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

    val currentLanguage = mutableStateOf(DefaultLanguage)

    fun updateCurrentLanguage() {
        val a = getLocale()
        val internalCurrentLanguage = getLocale()[0]?.language ?: DefaultLanguage
        currentLanguage.value =
            AvailableLanguages.find { it.locale.language == internalCurrentLanguage }
                ?: DefaultLanguage
    }

    fun changeLanguage(language: Language) {
        changeLocale(language.locale)
    }
}