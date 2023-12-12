package com.oscar.gamermvvmapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DialogCapturePicture(status: MutableState<Boolean>, takePhoto: () -> Unit, pickImage: () -> Unit) {
    if(status.value){
        AlertDialog(
            modifier = Modifier.fillMaxWidth().height(180.dp),
            onDismissRequest = { status.value = false},
            title = { Text(text = "Selecciona un aopcion", style = MaterialTheme.typography.titleMedium) },
            confirmButton = {
                Row (
                    modifier = Modifier.fillMaxWidth().padding(vertical = 30.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Button(modifier = Modifier.width(130.dp) ,
                        onClick = {
                            pickImage()
                            status.value = !status.value
                        }){
                        Text(text = "Galeria")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(modifier = Modifier.width(130.dp),
                        onClick = {
                            takePhoto()
                            status.value = !status.value
                        }){
                        Text(text = "Camara")
                    }
                }
            }
        )
    }
}