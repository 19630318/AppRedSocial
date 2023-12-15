package com.oscar.gamermvvmapp.presentation.screens.newpost

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.oscar.gamermvvmapp.presentation.components.DefaultButtom
import com.oscar.gamermvvmapp.presentation.components.DefaultTopBar
import com.oscar.gamermvvmapp.presentation.screens.newpost.components.NewPostContent
import com.oscar.gamermvvmapp.presentation.screens.newpost.components.NewPostStatus
import com.oscar.gamermvvmapp.presentation.utils.Vibrate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPostScreen(navHostController: NavHostController, vm: NewPostViewModel = hiltViewModel()) {

    val context = LocalContext.current

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
                    vm.wife = vm.isInternerAvalibleView()
                    if (vm.wife){
                        Vibrate.vibrar(context)
                        vm.onNewPost()
                    }else{
                        Toast.makeText(context, "No hay conexion a internet", Toast.LENGTH_LONG).show()
                    }
                }
            )
        }
    )
    NewPostStatus()

}