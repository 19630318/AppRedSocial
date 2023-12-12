package com.oscar.gamermvvmapp.presentation.screens.profile_edit

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.oscar.gamermvvmapp.domain.usecase.users.SaveImage
import com.oscar.gamermvvmapp.presentation.components.DefaultTopBar
import com.oscar.gamermvvmapp.presentation.screens.profile_edit.components.ProfileEditContent
import com.oscar.gamermvvmapp.presentation.screens.profile_edit.components.ProfileEditSaveImageStatus
import com.oscar.gamermvvmapp.presentation.screens.profile_edit.components.ProfileEditUpdateStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditScreen(navHostController: NavHostController, user: String) {

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Editar usuario",
                upAvailable = true,
                navHostController = navHostController
            )
        },
        content = {
            ProfileEditContent(it, navHostController)
        }
    )
    ProfileEditSaveImageStatus()
    ProfileEditUpdateStatus()
}