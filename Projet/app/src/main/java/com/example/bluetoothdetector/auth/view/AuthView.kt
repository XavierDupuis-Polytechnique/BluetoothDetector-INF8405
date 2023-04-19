package com.example.bluetoothdetector.auth.view

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bluetoothdetector.auth.domain.Redirection
import com.example.bluetoothdetector.auth.viewmodel.AuthViewModel
import com.example.bluetoothdetector.common.domain.Page
import com.example.bluetoothdetector.common.view.StaticSpinnerView
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.common.view.typography.Subtitle
import com.example.bluetoothdetector.common.view.typography.Title

@Composable
fun AuthView(
    page: Page,
    navController: NavHostController,
    authViewModel: AuthViewModel,
    redirection: Redirection,
    confirm: ((Context) -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    CenteredVerticalContainer {
        Title(page.denomination)
        Spacer(modifier = Modifier.size(16.dp))
        content()
        authViewModel.authState.error?.let {
            Subtitle(
                subtitle = it,
                color = MaterialTheme.colors.error
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        confirm?.let {
            ConfirmButton(page.description, confirm)
        }
        Spacer(modifier = Modifier.size(16.dp))
        Redirect(navController, authViewModel, redirection)
    }
}

@Composable
fun ConfirmButton(value: Int, confirm: (Context) -> Unit) {
    val context = LocalContext.current
    Button(onClick = { confirm(context) }) {
        Text(stringResource(value))
    }
}


@Composable
fun Redirect(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    redirection: Redirection,
) {
    CenteredHorizontalContainer {
        Subtitle(redirection.message)
        Spacer(modifier = Modifier.size(8.dp))
        TextButton(onClick = {
            redirection.action?.let { it(authViewModel) }
            authViewModel.navigate(navController, redirection.page)
        }) {
            Text(stringResource(redirection.label))
        }
    }

    StaticSpinnerView(authViewModel.authState.isLoading)
}