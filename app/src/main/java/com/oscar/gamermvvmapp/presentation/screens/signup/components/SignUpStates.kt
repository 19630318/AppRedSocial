package com.oscar.gamermvvmapp.presentation.screens.signup.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.oscar.gamermvvmapp.domain.model.Response
import com.oscar.gamermvvmapp.presentation.components.DefaultCircularProgressIndicator
import com.oscar.gamermvvmapp.presentation.navigation.Graph
import com.oscar.gamermvvmapp.presentation.navigation.screen.auth.AuthScreen
import com.oscar.gamermvvmapp.presentation.navigation.screen.root.RootScreen
import com.oscar.gamermvvmapp.presentation.screens.signup.SignupViewModel

@Composable
fun SignUpStates(navHostController: NavHostController,vm: SignupViewModel = hiltViewModel()) {

    val response = vm.signUpResponse

    when (response) {
        Response.Loading -> {
            DefaultCircularProgressIndicator()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
                vm.createUser()
                navHostController.navigate(route = Graph.HOME){
                    popUpTo(AuthScreen.Login.route){
                        inclusive = true
                    }
                }
            }
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, response.exception?.message ?: "Error desconocido", Toast.LENGTH_SHORT).show()
        }
        else -> {
            if (response != null) {
                Toast.makeText(LocalContext.current, "Hubo error desconocido", Toast.LENGTH_LONG).show()
            }
        }
    }

}