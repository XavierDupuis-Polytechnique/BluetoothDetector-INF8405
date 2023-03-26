package com.example.bluetoothdetector.common.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bluetoothdetector.common.domain.Language
import com.example.bluetoothdetector.common.repository.LanguageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val languageRepository: LanguageRepository
) : ViewModel() {

    val currentLanguage = languageRepository.currentLanguage

    private val selectedLanguage = mutableStateOf(languageRepository.currentLanguage.value)

    val isLanguageModalShown = mutableStateOf(false)

    fun showLanguages() {
        isLanguageModalShown.value = true
    }

    fun closeModal() {
        isLanguageModalShown.value = false
        selectedLanguage.value = currentLanguage.value
    }

    fun isCurrentLanguage(language: Language): Boolean {
        return languageRepository.currentLanguage.value === language
    }
    
    fun isSelectedLanguage(language: Language): Boolean {
        return selectedLanguage.value === language
    }

    fun selectLanguage(language: Language) {
        selectedLanguage.value = language
    }

    fun confirmSelectedLanguage() {
        languageRepository.changeLanguage(selectedLanguage.value)
    }

    fun canConfirmSelectedLanguage(): Boolean {
        return !isCurrentLanguage(selectedLanguage.value)
    }
}