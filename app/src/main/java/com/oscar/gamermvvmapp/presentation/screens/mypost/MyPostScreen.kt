package com.oscar.gamermvvmapp.presentation.screens.mypost

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.oscar.gamermvvmapp.presentation.navigation.screen.details.DetailsScreen
import com.oscar.gamermvvmapp.presentation.screens.mypost.components.MyPostListStatus
import com.oscar.gamermvvmapp.presentation.utils.Vibrate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPostScreen (navHostController: NavHostController, vm: MyPostViewModel = hiltViewModel()) {

    val context = LocalContext.current

    Scaffold (
        content = {
            MyPostListStatus(navHostController, it)
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 70.dp),
                onClick = {
                    vm.wife = vm.isInternerAvalibleView()
                    if (vm.wife){
                        navHostController.navigate(route = DetailsScreen.NewPost.route)
                        Vibrate.vibrar(context)
                    }else{
                        Toast.makeText(context, "No hay conexion a internet", Toast.LENGTH_LONG).show()
                    }
                }
            ){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add post"
                )
            }
        }
    )

}