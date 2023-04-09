package com.example.bluetoothdetector.common.view.languages

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
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
            val colors =
                if (viewModel.isSelectedLanguage(it))
                    ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colors.onPrimary,
                        backgroundColor = MaterialTheme.colors.primary
                    )
                else
                    ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colors.onSurface,
                        backgroundColor = MaterialTheme.colors.surface
                    )

            SelectableListItem(
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