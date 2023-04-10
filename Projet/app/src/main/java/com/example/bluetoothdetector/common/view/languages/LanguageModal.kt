package com.example.bluetoothdetector.common.view.languages

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.common.domain.action.Action
import com.example.bluetoothdetector.common.domain.modal.ModalActions
import com.example.bluetoothdetector.common.repository.LanguageRepository
import com.example.bluetoothdetector.common.view.SelectableListItem
import com.example.bluetoothdetector.common.view.modal.Modal
import com.example.bluetoothdetector.common.viewmodel.LanguageViewModel

@Composable
fun LanguagesModal(
    viewModel: LanguageViewModel
) {
    Modal(
        title = R.string.choose_language,
        closeModal = { viewModel.closeModal() }
    ) {
        LanguageRepository.AvailableLanguages.forEach {
            SelectableListItem(
                modifier = Modifier.fillMaxWidth(0.4f),
                isSelected = { viewModel.isSelectedLanguage(it) },
                select = { viewModel.selectLanguage(it) },
                mainText = it.denomination,
                icon = it.abbreviation
            )
        }
        it(
            ModalActions(
                primary = Action(
                    label = { R.string.confirm },
                    execute = { viewModel.confirmSelectedLanguage() },
                    // TODO : REVIEW UX
                    // canExecute = { viewModel.canConfirmSelectedLanguage() }
                )
            )
        )
    }
}