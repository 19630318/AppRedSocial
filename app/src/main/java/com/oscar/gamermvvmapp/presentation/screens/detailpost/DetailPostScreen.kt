package com.oscar.gamermvvmapp.presentation.screens.detailpost

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.oscar.gamermvvmapp.presentation.screens.detailpost.components.DetailPostContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPostScreen(navHostController: NavHostController, post: String) {

    Log.d("DetailPostScreen", "Post: $post")

    Scaffold(
        content = {
            DetailPostContent(navHostController, it)
        }
    )

}