package com.oscar.gamermvvmapp.presentation.screens.profile_edit.components

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.oscar.gamermvvmapp.R
import com.oscar.gamermvvmapp.presentation.components.DefaultButtom
import com.oscar.gamermvvmapp.presentation.components.DefaultTextField
import com.oscar.gamermvvmapp.presentation.components.DialogCapturePicture
import com.oscar.gamermvvmapp.presentation.screens.profile_edit.ProfileEditViewModel
import com.oscar.gamermvvmapp.presentation.utils.ComposeFileProvider

@Composable
fun ProfileEditContent(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    viewModel: ProfileEditViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    viewModel.resultHandler.handle()

    var dialogState = remember { mutableStateOf(false) }

    DialogCapturePicture(
        status = dialogState,
        takePhoto = { viewModel.takePhoto() },
        pickImage = { viewModel.pickImage() }
    )

    Box(
        modifier = Modifier.fillMaxWidth().padding(paddingValues)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(250.dp).background(MaterialTheme.colorScheme.primary)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                if (viewModel.state.image != "") {
                    AsyncImage(
                        modifier = Modifier.size(150.dp).clip(CircleShape).clickable {
                            dialogState.value = !dialogState.value
                        },
                        model = viewModel.state.image,
                        contentDescription = "SelectedImage",
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        modifier = Modifier.size(150.dp).clickable {
                            dialogState.value = !dialogState.value
                        },
                        painter = painterResource(R.drawable.user),
                        contentDescription = "Image user",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        Card(
            modifier = Modifier.fillMaxWidth().padding(top = 200.dp, start = 40.dp, end = 40.dp),
            shape = Shapes().small,
            elevation = CardDefaults.cardElevation(15.dp)
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Text(
                    modifier = Modifier.padding(top = 30.dp, start = 20.dp).align(Alignment.Start),
                    text = "EDITAR",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier.padding(top = 10.dp, start = 20.dp, bottom = 20.dp).align(Alignment.Start),
                    text = "Por favor ingresa estos datos para continuar",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(15.dp))
                DefaultTextField(
                    value = viewModel.state.userName,
                    onChangeValue = {
                        viewModel.onUserNameInput(it)
                    },
                    placeHolder = "Nombre usuario",
                    icon = Icons.Default.Person,
                    keyboardType = KeyboardType.Text,
                    hideText = false,
                    validateField = {
                        viewModel.validUserName()
                    },
                    errorMsg = viewModel.userNameErrorMgs
                )
                Spacer(modifier = Modifier.height(25.dp))
                DefaultButtom(
                    onClick = {
                        if(viewModel.isInternerAvalibleView()){
                            if ( viewModel.file == null){
                                viewModel.onUpdate(viewModel.state.image)
                            }else {
                                viewModel.saveImage()
                            }
                        }else{
                            Toast.makeText(
                                context,
                                "No tienes conexion a internet, porfavor \n intentelo mas tarde ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    text = "ACTUALZAR",
                    colorButtom = MaterialTheme.colorScheme.primary,
                )
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }

}