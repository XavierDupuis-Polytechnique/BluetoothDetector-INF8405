package com.example.bluetoothdetector.common.view.modal

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.bluetoothdetector.common.domain.modal.ModalActions
import com.example.bluetoothdetector.common.domain.modal.ModalResult
import com.example.bluetoothdetector.common.view.containers.CardContainer
import com.example.bluetoothdetector.common.view.typography.Title


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Modal(
    title: Int?,
    closeModal: (modalResult: ModalResult) -> Unit,
    content: @Composable (
        modalButtons: @Composable (
            modalActions: ModalActions
        ) -> Unit
    ) -> Unit,
) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { },
        content = {
            CardContainer {
                Column(
                    modifier = Modifier
                        .padding(18.dp)
                ) {
                    title?.let {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 18.dp)
                        ) {
                            Title(stringResource(it))
                        }
                    }

                    content { modalActions ->
                        Spacer(modifier = Modifier.padding(vertical = 18.dp))
                        Row(
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            ModalButton(
                                { closeModal(ModalResult.Cancel) },
                                modalActions.cancel,
                                backgroundColor = MaterialTheme.colors.secondary,
                                contentColor = MaterialTheme.colors.onSecondary,
                            )

                            modalActions.primary?.let {
                                ModalButton(
                                    { closeModal(ModalResult.Primary) },
                                    it
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}
