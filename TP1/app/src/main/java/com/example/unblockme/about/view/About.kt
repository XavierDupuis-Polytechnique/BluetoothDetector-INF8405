package com.example.unblockme.about.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unblockme.about.viewmodel.AboutViewModel
import com.example.unblockme.common.view.CenteredVerticalContainer
import com.example.unblockme.common.view.Page

@Composable
fun About(
    navigateTo: (Page) -> Unit,
    viewModel: AboutViewModel = viewModel(),
) {
    CenteredVerticalContainer {
        Text(" - ABOUT PAGE - ")
        // TODO : Project member names
        Button(
            modifier = Modifier.fillMaxWidth(0.6f),
            onClick = { viewModel.navigateToMenu(navigateTo) }
        ) {
            Text("BACK TO MENU")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutPreview(viewModel: AboutViewModel = viewModel()) {
    About({})
}