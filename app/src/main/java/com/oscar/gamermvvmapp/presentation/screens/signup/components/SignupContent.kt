package com.oscar.gamermvvmapp.presentation.screens.signup.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.oscar.gamermvvmapp.R
import com.oscar.gamermvvmapp.presentation.components.DefaultButtom
import com.oscar.gamermvvmapp.presentation.components.DefaultTextField
import com.oscar.gamermvvmapp.presentation.screens.signup.SignupViewModel

@Composable
fun SignupContent(paddingValues: PaddingValues, viewModel: SignupViewModel = hiltViewModel()) {

    Box(
        modifier = Modifier.fillMaxWidth().padding(paddingValues)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(250.dp).background(MaterialTheme.colorScheme.primary)
        ){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    modifier = Modifier.height(150.dp),
                    painter = painterResource(R.drawable.user),
                    contentDescription = "Image user"
                )
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
                    text = "REGISTRO",
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
                Spacer(modifier = Modifier.height(15.dp))
                DefaultTextField(
                    value = viewModel.state.email,
                    onChangeValue = {
                        viewModel.onEmailInput(it)
                    },
                    placeHolder = "Correo electronico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    hideText = false,
                    validateField = {
                        viewModel.validEmail()
                    },
                    errorMsg = viewModel.emailErrorMgs
                )
                Spacer(modifier = Modifier.height(15.dp))
                DefaultTextField(
                    value = viewModel.state.password,
                    onChangeValue = {
                        viewModel.onPasswordInput(it)
                    },
                    placeHolder = "Contraseña",
                    icon = Icons.Default.Lock,
                    keyboardType = KeyboardType.Password,
                    hideText = true,
                    validateField = {
                        viewModel.validPassword()
                    },
                    errorMsg = viewModel.passwordErrorMgs
                )
                Spacer(modifier = Modifier.height(15.dp))
                DefaultTextField(
                    value = viewModel.state.confrimPassword,
                    onChangeValue = {
                        viewModel.onConfirmPasswordInput(it)
                    },
                    placeHolder = "Repetir contraseña",
                    icon = Icons.Outlined.Lock,
                    keyboardType = KeyboardType.Password,
                    hideText = true,
                    validateField = {
                        viewModel.validConfirmPassword()
                    },
                    errorMsg = viewModel.confirmPasswordErrorMgs
                )
                Spacer(modifier = Modifier.height(25.dp))
                DefaultButtom(
                    onClick = {
                        viewModel.onSignUp()
                    },
                    text = "REGISTRARSE",
                    colorButtom = MaterialTheme.colorScheme.primary,
                    enabled = viewModel.isEnabledLoginButtom
                )
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }

}
