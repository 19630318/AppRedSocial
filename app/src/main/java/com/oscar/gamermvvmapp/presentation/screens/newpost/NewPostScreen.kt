package com.oscar.gamermvvmapp.presentation.screens.newpost

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.oscar.gamermvvmapp.presentation.components.DefaultButtom
import com.oscar.gamermvvmapp.presentation.components.DefaultTopBar
import com.oscar.gamermvvmapp.presentation.screens.newpost.components.NewPostContent
import com.oscar.gamermvvmapp.presentation.screens.newpost.components.NewPostStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPostScreen(navHostController: NavHostController, vm: NewPostViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {
                 DefaultTopBar(
                     title = "Nuevo post",
                     upAvailable = true,
                     navHostController = navHostController
                 )
        },
        content = {
            NewPostContent(it)
        },
        bottomBar = {
            DefaultButtom(
                text = "PUBLICAR",
                onClick = {
                    vm.onNewPost()
                }
            )
        }
    )
    NewPostStatus()

}