package com.oscar.gamermvvmapp.presentation.screens.updatepost.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.oscar.gamermvvmapp.R
import com.oscar.gamermvvmapp.presentation.components.DefaultTextField
import com.oscar.gamermvvmapp.presentation.components.DialogCapturePicture
import com.oscar.gamermvvmapp.presentation.screens.updatepost.UpdatePostViewModel

@Composable
fun UpdatePostContent(paddingValues: PaddingValues, vm: UpdatePostViewModel = hiltViewModel()) {

    vm.resultHandler.handle()

    var dialogState = remember { mutableStateOf(false) }

    DialogCapturePicture(
        status = dialogState,
        takePhoto = { vm.takePhoto() },
        pickImage = { vm.pickImage() }
    )

    Box(
        modifier = Modifier.fillMaxWidth().padding(paddingValues)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().height(200.dp).background(MaterialTheme.colorScheme.primary)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (vm.state.image != "") {
                        AsyncImage(
                            modifier = Modifier.fillMaxWidth().height(200.dp).clickable {
                                dialogState.value = !dialogState.value
                            },
                            model = vm.state.image,
                            contentDescription = "SelectedImage",
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(
                            modifier = Modifier.size(150.dp).clickable {
                                dialogState.value = !dialogState.value
                            },
                            painter = painterResource(R.drawable.add_image),
                            contentDescription = "Image user",
                            contentScale = ContentScale.Crop
                        )
                        Text(text = "Selecciona una imagen", style = MaterialTheme.typography.titleLarge)
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            DefaultTextField(
                value = vm.state.name,
                onChangeValue = {
                    vm.onNameInput(it)
                },
                placeHolder = "Nombre del juego",
                icon = Icons.Default.Gamepad,
                keyboardType = KeyboardType.Text,
                hideText = false,
                validateField = {
                    //viewModel.validUserName()
                },
                errorMsg = ""
            )
            Spacer(modifier = Modifier.height(15.dp))
            DefaultTextField(
                value = vm.state.description,
                onChangeValue = {
                    vm.onDescriptionInput(it)
                },
                placeHolder = "Descripción",
                icon = Icons.Default.List,
                keyboardType = KeyboardType.Text,
                hideText = false,
                validateField = {
                    //viewModel.validUserName()
                },
                errorMsg = ""
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Categorias", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(15.dp))
            vm.radioOptions.forEach {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                        .selectable(selected = (it.categoria == vm.state.category), onClick = {
                            vm.onCategoryInput(it.categoria)
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (it.categoria == vm.state.category),
                        onClick = { vm.onCategoryInput(it.categoria) }
                    )
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.width(100.dp).padding(10.dp),
                            text = it.categoria,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Image(
                            modifier = Modifier.height(50.dp).padding(8.dp),
                            painter = painterResource(it.imagen),
                            contentDescription = null
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}