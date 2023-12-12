package com.oscar.gamermvvmapp.presentation.screens.updatepost.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.oscar.gamermvvmapp.domain.model.Response
import com.oscar.gamermvvmapp.presentation.components.DefaultCircularProgressIndicator
import com.oscar.gamermvvmapp.presentation.screens.newpost.NewPostViewModel
import com.oscar.gamermvvmapp.presentation.screens.updatepost.UpdatePostViewModel

@Composable
fun UpdatePostStatus(vm: UpdatePostViewModel = hiltViewModel()) {

    val response = vm.updatePostResponse

    when (response) {
        Response.Loading -> {
            DefaultCircularProgressIndicator()
        }
        is Response.Success -> {
            //vm.clearForm()
            Toast.makeText(LocalContext.current, "Se actualizo el post correctamente", Toast.LENGTH_SHORT).show()
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