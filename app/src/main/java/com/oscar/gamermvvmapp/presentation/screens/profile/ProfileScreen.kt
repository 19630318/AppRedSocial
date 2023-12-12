package com.oscar.gamermvvmapp.presentation.screens.profile

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.oscar.gamermvvmapp.presentation.screens.profile.components.ProfileContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navHostController: NavHostController, vm: ProfileViewModel = hiltViewModel()) {



    Scaffold(
        content = {
            ProfileContent(it, navHostController)
        }
    )

}