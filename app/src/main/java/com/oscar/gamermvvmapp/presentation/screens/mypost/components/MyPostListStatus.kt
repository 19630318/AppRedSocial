package com.oscar.gamermvvmapp.presentation.screens.mypost.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.oscar.gamermvvmapp.domain.model.Response
import com.oscar.gamermvvmapp.presentation.components.DefaultCircularProgressIndicator
import com.oscar.gamermvvmapp.presentation.screens.mypost.MyPostViewModel
import com.oscar.gamermvvmapp.presentation.screens.postlist.components.PostListContent


@Composable
fun MyPostListStatus(navHostController: NavHostController, paddingValues: PaddingValues, vm: MyPostViewModel = hiltViewModel()) {

    val response = vm.postsUserResponse

    when (response) {
        Response.Loading -> {
            DefaultCircularProgressIndicator()
        }
        is Response.Success -> {
            MyPostContent(navHostController, paddingValues, response.data)
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