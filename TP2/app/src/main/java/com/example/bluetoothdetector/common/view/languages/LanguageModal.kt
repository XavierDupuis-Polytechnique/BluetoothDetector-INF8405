package com.example.bluetoothdetector.common.view.languages

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.common.domain.action.Action
import com.example.bluetoothdetector.common.domain.modal.ModalActions
import com.example.bluetoothdetector.common.repository.LanguageRepository
import com.example.bluetoothdetector.common.view.Modal
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
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
                if (viewModel.isCurrentLanguage(it))
                    ButtonDefaults.outlinedButtonColors()
                else
                    ButtonDefaults.textButtonColors()
            Button(
                onClick = { viewModel.selectLanguage(it) },
                colors = colors
            ) {
                CenteredHorizontalContainer {
                    Text(stringResource(it.denomination))
                    Text(stringResource(it.abbreviation))
                }
            }
        }
        it(
            ModalActions(
                primary = Action(
                    label = { R.string.confirm },
                    execute = { viewModel.confirmSelectedLanguage() },
                    canExecute = { viewModel.canConfirmSelectedLanguage() }
                )
            )
        )
    }
}