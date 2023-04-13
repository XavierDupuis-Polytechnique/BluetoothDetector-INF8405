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
import com.example.bluetoothdetector.auth.viewmodel.AuthViewModel
import com.example.bluetoothdetector.common.domain.Page
import com.example.bluetoothdetector.common.view.SpinnerView
import com.example.bluetoothdetector.common.view.containers.CenteredHorizontalContainer
import com.example.bluetoothdetector.common.view.containers.CenteredVerticalContainer
import com.example.bluetoothdetector.common.view.typography.Subtitle
import com.example.bluetoothdetector.common.view.typography.Title

@Composable
fun AuthView(
    title: String,
    navController: NavHostController,
    authViewModel: AuthViewModel,
    confirm: (Context) -> Unit,
    redirectPage: Page,
    redirectMessage: String,
    fields: @Composable () -> Unit,
) {
    CenteredVerticalContainer {
        Title(title)
        authViewModel.authState.signUpError?.let {
            Subtitle(
                subtitle = it,
                color = MaterialTheme.colors.error
            )
        }
        fields()
        Spacer(modifier = Modifier.size(16.dp))
        ConfirmButton(title, confirm)
        Spacer(modifier = Modifier.size(16.dp))
        Redirect(navController, authViewModel, redirectPage, redirectMessage)
    }
}

@Composable
fun ConfirmButton(title: String, confirm: (Context) -> Unit) {
    val context = LocalContext.current
    Button(onClick = { confirm(context) }) {
        Text(text = title)
    }
}


@Composable
fun Redirect(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    redirectPage: Page,
    redirectMessage: String
) {
    CenteredHorizontalContainer {
        Subtitle(redirectMessage)
        Spacer(modifier = Modifier.size(8.dp))
        TextButton(onClick = { authViewModel.navigate(navController, redirectPage)}) {
            Text(stringResource(redirectPage.denomination))
        }

    }

    if (authViewModel.authState.isLoading){
        SpinnerView()
    }

//    LaunchedEffect(key1 = AuthViewModel?.hasUser){
//        if (AuthViewModel?.hasUser == true){
//            onNavToHomePage.invoke()
//        }
//    }
}