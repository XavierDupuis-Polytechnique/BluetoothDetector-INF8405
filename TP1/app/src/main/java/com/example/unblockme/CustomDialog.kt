package com.example.unblockme

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.unblockme.ui.theme.Purple700
import com.example.unblockme.ui.theme.SkyBlue
import com.example.unblockme.ui.theme.Teal200

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomDialog(
    onDismiss:()->Unit,
    onConfim:()->Unit
){

    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            elevation = 5.dp,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .border(1.dp, color = SkyBlue, shape = RoundedCornerShape(15.dp))
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ){
                Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 93.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Puzzle Solved",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

            }


                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth()
                            .padding(70.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Text(text = "Cleared",
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.h3,
                                    textAlign = TextAlign.Center)

                    }
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ){

                    }




                }

                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Button(
                        onClick = {
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Teal200,
                            contentColor = Purple700
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Cancel",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                            )
                    }
                    Button(
                        onClick = {
                            onConfim()
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Teal200,
                            contentColor = Purple700
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Confirm",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                
            }
        }


    }

}