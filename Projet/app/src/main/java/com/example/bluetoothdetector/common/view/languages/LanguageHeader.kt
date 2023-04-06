package com.example.bluetoothdetector.common.view.languages

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bluetoothdetector.common.viewmodel.LanguageViewModel

@Composable
fun LanguagesHeader(
    viewModel: LanguageViewModel = hiltViewModel()
) {
    TextButton(onClick = { viewModel.showLanguages() }) {
        Text(stringResource(viewModel.currentLanguage.value.abbreviation))
    }
    if (viewModel.isLanguageModalShown.value) {
        LanguagesModal(viewModel)
    }
}