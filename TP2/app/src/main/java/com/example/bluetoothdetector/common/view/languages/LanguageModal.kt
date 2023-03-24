package com.example.bluetoothdetector.common.view.languages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bluetoothdetector.R
import com.example.bluetoothdetector.common.domain.action.Action
import com.example.bluetoothdetector.common.domain.modal.ModalActions
import com.example.bluetoothdetector.common.repository.LanguageRepository
import com.example.bluetoothdetector.common.view.modal.Modal
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
                if (viewModel.isSelectedLanguage(it))
                    ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colors.onPrimary,
                        backgroundColor = MaterialTheme.colors.primary
                    )
                else
                    ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colors.onSecondary,
                        backgroundColor = MaterialTheme.colors.secondary
                    )

            Button(
                enabled = !viewModel.isCurrentLanguage(it),
                onClick = { viewModel.selectLanguage(it) },
                colors = colors
            ) {
                CenteredHorizontalContainer {
                    Text(stringResource(it.denomination))
                    Spacer(modifier = Modifier.padding(8.dp))
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