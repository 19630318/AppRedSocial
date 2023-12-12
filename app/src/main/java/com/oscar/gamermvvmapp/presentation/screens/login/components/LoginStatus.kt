package com.oscar.gamermvvmapp.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.oscar.gamermvvmapp.domain.model.Response
import com.oscar.gamermvvmapp.presentation.components.DefaultCircularProgressIndicator
import com.oscar.gamermvvmapp.presentation.navigation.Graph
import com.oscar.gamermvvmapp.presentation.navigation.screen.root.RootScreen
import com.oscar.gamermvvmapp.presentation.screens.login.LoginViewModel

@Composable
fun LoginStatus(navHostController: NavHostController,vm: LoginViewModel = hiltViewModel()) {

    val response = vm.loginResponse

    when (response) {
        Response.Loading -> {
            DefaultCircularProgressIndicator()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
                navHostController.navigate(route = Graph.HOME){
                    popUpTo(Graph.AUTHENTICATION){
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