package com.oscar.gamermvvmapp.presentation.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.oscar.gamermvvmapp.R
import com.oscar.gamermvvmapp.presentation.components.DefaultButtom
import com.oscar.gamermvvmapp.presentation.components.DefaultTextField
import com.oscar.gamermvvmapp.presentation.screens.login.LoginViewModel
import com.oscar.gamermvvmapp.presentation.utils.Vibrate


@Composable
fun LoginContent(paddingValues: PaddingValues, viewModel: LoginViewModel = hiltViewModel()) {

    //Otra forma de manejar los estados
    /*val loginFlow = viewModel.loginFlow.collectAsState()*/

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
                Image(
                    modifier = Modifier.height(150.dp).padding(top = 20.dp),
                    painter = painterResource(R.drawable.control),
                    contentDescription = "Control xbox"
                )
                Text(text = "FIREBASE MVVM", style = MaterialTheme.typography.titleLarge)
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
                    text = "LOGIN",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier.padding(top = 10.dp, start = 20.dp, bottom = 20.dp).align(Alignment.Start),
                    text = "Por favor inicia sesión para continuar",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(15.dp))
                DefaultTextField(
                    value = viewModel.state.email,
                    onChangeValue = { viewModel.onEmailInput(it) },
                    placeHolder = "Correo electronico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    hideText = false,
                    errorMsg = viewModel.emailErrorMgs,
                    validateField = {
                        viewModel.validEmail()
                    }
                )
                Spacer(modifier = Modifier.height(15.dp))
                DefaultTextField(
                    value = viewModel.state.password,
                    onChangeValue = { viewModel.onPasswordInput(it) },
                    placeHolder = "Contraseña",
                    icon = Icons.Default.Lock,
                    keyboardType = KeyboardType.Password,
                    hideText = true,
                    errorMsg = viewModel.passwordErrorMgs,
                    validateField = {
                        viewModel.validPassword()
                    }
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = viewModel.saveEmail,
                        onCheckedChange = { viewModel.saveEmail = it }
                    )
                    Text(text = "Recordar email", style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.height(25.dp))
                DefaultButtom(
                    onClick = {
                        viewModel.login()
                        viewModel.saveEmail()
                    },
                    text = "INICIAR SESIÓN",
                    colorButtom = MaterialTheme.colorScheme.primary,
                    enabled = viewModel.isEnabledLoginButtom
                )
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }

    //Otra forma de manejar los estados
    /*loginFlow.value.let {
        when (it) {
            Response.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator() }
            }
            is Response.Success -> {
                Toast.makeText(LocalContext.current, "Usuario Logiado", Toast.LENGTH_SHORT).show()
            }
            is Response.Failure -> {
                Toast.makeText(LocalContext.current, it.exception?.message ?: "Error desconocido", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(LocalContext.current, "Error desconocido", Toast.LENGTH_SHORT).show()
            }
        }
    }*/

}

