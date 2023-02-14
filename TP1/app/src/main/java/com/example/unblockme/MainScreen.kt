package com.example.unblockme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.unblockme.game.domain.GameManager
import com.example.unblockme.ui.theme.Purple700
import com.example.unblockme.ui.theme.Teal200

@Composable
fun MainScreen(
    viewModel: MainViewModel
){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter){
        Button(
            onClick = {
                viewModel.onBuyClick()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Teal200,
                contentColor = Purple700
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = CircleShape
        ) {
           Text(
               text = "Success",
               style = MaterialTheme.typography.h5,
               fontWeight = FontWeight.Bold,
               textAlign = TextAlign.Center
           )
        }
    }
    if (viewModel.isSuccessShown.value){
        CustomDialog(onDismiss = {
            viewModel.onDismissDialog()
        },
            onConfim = {

                viewModel.selectNextLevel()
//                GameManager.currentLevel.value++

                viewModel.onDismissDialog()
                viewModel.selectNextLevel()

            }


            )


    }
}