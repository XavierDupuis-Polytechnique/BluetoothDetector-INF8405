package com.example.unblockme.about.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unblockme.about.viewmodel.AboutViewModel
import com.example.unblockme.common.view.CenteredHorizontalContainer
import com.example.unblockme.common.view.CenteredVerticalContainer
import com.example.unblockme.common.view.Page
import com.example.unblockme.game.domain.GameManager
import com.example.unblockme.ui.theme.SkyBlue

// About page
@Composable
fun About(
    navigateTo: (Page) -> Unit,
    viewModel: AboutViewModel = viewModel(),
) {
    CenteredVerticalContainer {
        // Names of the developers
        Text(" - App developed by - \n", color = Color.White)
        Text(
            "Xavier Dupuis\nWilliam Lévesque\nMarie Noël\nMohammed Imade MEDDANE\n",
            color = Color.White,
            textAlign = TextAlign.Center
        )

        // Button that makes it easy to reset the user best scores cache
        CenteredHorizontalContainer(Modifier.fillMaxWidth(0.8f)) {
            Button(
                colors = buttonColors(backgroundColor = SkyBlue),
                modifier = Modifier.padding(start = 1.dp),
                onClick = { GameManager.resetCache() }

            ) {
                Text("Reset best scores cache")
            }
        }
        // Button that returns to the main menu
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