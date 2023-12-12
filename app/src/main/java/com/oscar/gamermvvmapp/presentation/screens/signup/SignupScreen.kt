package com.oscar.gamermvvmapp.presentation.screens.signup

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.oscar.gamermvvmapp.presentation.components.DefaultTopBar
import com.oscar.gamermvvmapp.presentation.screens.signup.components.SignUpStates
import com.oscar.gamermvvmapp.presentation.screens.signup.components.SignupContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Nuevo usuario",
                upAvailable = true,
                navHostController = navHostController
            )
        },
        content = {
            SignupContent(it)
        },
        bottomBar = {}
    )
    SignUpStates(navHostController)
}