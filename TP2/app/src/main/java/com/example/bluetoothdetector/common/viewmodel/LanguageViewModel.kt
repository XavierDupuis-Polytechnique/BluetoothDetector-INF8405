package com.example.bluetoothdetector.common.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bluetoothdetector.common.domain.English
import com.example.bluetoothdetector.common.domain.Language
import com.example.bluetoothdetector.common.domain.Languages
import com.example.bluetoothdetector.common.domain.modal.ModalResult
import com.example.bluetoothdetector.common.repository.LanguageRepository
import com.example.bluetoothdetector.main.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val languageRepository: LanguageRepository
) : ViewModel() {

    private val currentLanguage = mutableStateOf(English)

    val availableLanguages: List<Language> = Languages

    val selectedLanguage = mutableStateOf(English)

    val isLanguageModalShown = mutableStateOf(false)

    fun showLanguages() {
        isLanguageModalShown.value = true
    }

    fun closeModal() {
        isLanguageModalShown.value = false
    }

    fun isCurrentLanguage(language: Language): Boolean {
        return currentLanguage.value === language
    }

    fun selectLanguage(language: Language) {
        selectedLanguage.value = language
    }

    fun confirmSelectedLanguage() {
        currentLanguage.value = selectedLanguage.value
        languageRepository.printCurrentLocale()
        languageRepository.changeLocale()
    }

    fun canConfirmSelectedLanguage(): Boolean {
        return !isCurrentLanguage(selectedLanguage.value)
    }
}