package com.example.unblockme.common.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.unblockme.ui.theme.SkyBlue

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomDialog(
    onDismiss:()->Unit,
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
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(15.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        Row (
                            modifier = Modifier.fillMaxWidth()
                                .padding(20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(text = "You solved the puzzle!",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.h5,
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


                }
                
            }
        }


    }

    }}