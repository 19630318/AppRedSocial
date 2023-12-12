package com.oscar.gamermvvmapp.presentation.screens.login

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.oscar.gamermvvmapp.presentation.screens.login.components.LoginBottomBar
import com.oscar.gamermvvmapp.presentation.screens.login.components.LoginContent
import com.oscar.gamermvvmapp.presentation.screens.login.components.LoginStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navHostController: NavHostController) {

    Scaffold(
        content = {
            LoginContent(it)
        },
        bottomBar = {
            LoginBottomBar(navHostController)
        }
    )
    LoginStatus(navHostController)
}
