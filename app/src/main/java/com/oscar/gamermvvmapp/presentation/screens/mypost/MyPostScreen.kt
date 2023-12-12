package com.oscar.gamermvvmapp.presentation.screens.mypost

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.oscar.gamermvvmapp.presentation.navigation.screen.details.DetailsScreen
import com.oscar.gamermvvmapp.presentation.screens.mypost.components.MyPostListStatus
import com.oscar.gamermvvmapp.presentation.utils.Vibrate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPostScreen (navHostController: NavHostController) {

    val context = LocalContext.current

    Scaffold (
        content = {
            MyPostListStatus(navHostController, it)
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 70.dp),
                onClick = {
                    navHostController.navigate(route = DetailsScreen.NewPost.route)
                    Vibrate.vibrar(context)
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