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

    // Holds the current application language
    val currentLanguage = languageRepository.currentLanguage

    // Holds the currently selected language
    private val selectedLanguage = mutableStateOf(languageRepository.currentLanguage.value)

    // Holds the current language modal visibility
    val isLanguageModalShown = mutableStateOf(false)

    // Displays the languages modal
    fun showLanguages() {
        selectedLanguage.value = currentLanguage.value
        isLanguageModalShown.value = true
    }

    // Hides the languages modal
    fun hideLanguages() {
        isLanguageModalShown.value = false
    }

    // Compares the provided language to the current language
    private fun isCurrentLanguage(language: Language): Boolean {
        return languageRepository.currentLanguage.value === language
    }

    // Compares the provided language to the selected language
    fun isSelectedLanguage(language: Language): Boolean {
        return selectedLanguage.value === language
    }

    // Changes the selected language with the provided language
    fun selectLanguage(language: Language) {
        selectedLanguage.value = language
    }

    // Confirms language selection
    fun confirmSelectedLanguage() {
        languageRepository.changeLanguage(selectedLanguage.value)
    }
}