package com.example.bluetoothdetector.common.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.bluetoothdetector.common.domain.action.Action
import com.example.bluetoothdetector.common.domain.modal.ModalActions
import com.example.bluetoothdetector.common.domain.modal.ModalResult
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
            Card {
                Surface {
                    Column(
                        modifier = Modifier
                            .padding(18.dp)
                    ) {
                        if (title != null) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = 18.dp)
                            ) {
                                Title(stringResource(title))
                            }
                        }

                        content { modalActions ->
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.padding(top = 18.dp)
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
        }
    )
}

@Composable
private fun ModalButton(
    closeModal: () -> Unit,
    action: Action,
    backgroundColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = MaterialTheme.colors.onPrimary,
) {
    Button(
        modifier = Modifier
            .padding(end = 8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        onClick = {
            action.execute()
            closeModal()
        },
        enabled = action.canExecute()
    ) {
        Text(stringResource(action.label()))
    }
}
