package com.oscar.gamermvvmapp.presentation.screens.postlist

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.oscar.gamermvvmapp.presentation.screens.postlist.components.DeleteLikePostStatus
import com.oscar.gamermvvmapp.presentation.screens.postlist.components.GetPostStatus
import com.oscar.gamermvvmapp.presentation.screens.postlist.components.LikePostStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListScreen(navHostController: NavHostController) {

    Scaffold (
        content = {
            GetPostStatus(navHostController, it)
        }
    )

    LikePostStatus()
    DeleteLikePostStatus()

}

