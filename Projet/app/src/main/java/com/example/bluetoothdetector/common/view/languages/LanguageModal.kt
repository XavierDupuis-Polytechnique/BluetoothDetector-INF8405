package com.example.bluetoothdetector.common.view.languages

import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.common.domain.action.Action
import com.example.bluetoothdetector.common.domain.modal.ModalActions
import com.example.bluetoothdetector.common.repository.LanguageRepository
import com.example.bluetoothdetector.common.view.SelectableListItem
import com.example.bluetoothdetector.common.view.modal.Modal
import com.example.bluetoothdetector.common.viewmodel.LanguageViewModel

// Displays the current language and other available languages
@Composable
fun LanguagesModal(
    viewModel: LanguageViewModel
) {
    Modal(
        title = R.string.choose_language,
        closeModal = { viewModel.hideLanguages() }
    ) {
        LazyColumn {
            items(LanguageRepository.AvailableLanguages) {
                SelectableListItem(
                    modifier = Modifier.widthIn(max = 300.dp),
                    isSelected = { viewModel.isSelectedLanguage(it) },
                    select = { viewModel.selectLanguage(it) },
                    mainText = it.denomination,
                    icon = it.abbreviation
                )
            }
        }
        it(
            ModalActions(
                primary = Action(
                    label = { R.string.confirm },
                    execute = { viewModel.confirmSelectedLanguage() },
                )
            )
        )
    }
}