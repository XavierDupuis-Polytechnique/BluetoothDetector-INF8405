package com.example.bluetoothdetector.common.repository

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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

    lateinit var recreate: () -> Unit

    lateinit var currentLanguage: MutableState<Language>

    fun getLanguageFromLocale(
        locale: Locale = getCurrentLocale()
    ): Language {
        return AvailableLanguages.find { it.locale == locale }
            ?: DefaultLanguage
    }

    private fun getCurrentLocale(): Locale {
        return context.resources.configuration.locales[0]
    }

    fun changeLanguage(language: Language) {
        val configuration = context.resources.configuration
        configuration.setLocale(language.locale)
        // TODO : FIX DEPRECATION
        context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
        recreate()
    }
}