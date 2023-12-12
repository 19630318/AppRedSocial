package com.oscar.gamermvvmapp.presentation.screens.postlist.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.oscar.gamermvvmapp.domain.model.Response
import com.oscar.gamermvvmapp.presentation.components.DefaultCircularProgressIndicator
import com.oscar.gamermvvmapp.presentation.navigation.Graph
import com.oscar.gamermvvmapp.presentation.screens.postlist.PostViewModel


@Composable
fun LikePostStatus(vm: PostViewModel = hiltViewModel()) {

    val response = vm.likeResponse

    when (response) {
        Response.Loading -> {
            //DefaultCircularProgressIndicator()
        }
        is Response.Success -> {
            Toast.makeText(LocalContext.current, "Me gusta", Toast.LENGTH_SHORT).show()
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