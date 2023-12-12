package com.oscar.gamermvvmapp.presentation.screens.updatepost

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.oscar.gamermvvmapp.presentation.components.DefaultButtom
import com.oscar.gamermvvmapp.presentation.components.DefaultTopBar
import com.oscar.gamermvvmapp.presentation.screens.newpost.components.NewPostContent
import com.oscar.gamermvvmapp.presentation.screens.newpost.components.NewPostStatus
import com.oscar.gamermvvmapp.presentation.screens.updatepost.components.UpdatePostContent
import com.oscar.gamermvvmapp.presentation.screens.updatepost.components.UpdatePostStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePostScreen(navHostController: NavHostController, post: String, vm: UpdatePostViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {
                 DefaultTopBar(
                     title = "Editar post",
                     upAvailable = true,
                     navHostController = navHostController
                 )
        },
        content = {
            UpdatePostContent(it)
        },
        bottomBar = {
            DefaultButtom(
                text = "GUARDAR",
                onClick = {
                    vm.onUpdatePost()
                }
            )
        }
    )
    UpdatePostStatus()

}