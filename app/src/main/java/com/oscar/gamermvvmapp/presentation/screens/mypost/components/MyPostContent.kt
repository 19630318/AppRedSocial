package com.oscar.gamermvvmapp.presentation.screens.mypost.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlaylistRemove
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.oscar.gamermvvmapp.domain.model.Post
import com.oscar.gamermvvmapp.presentation.components.DefaultButtom
import com.oscar.gamermvvmapp.presentation.screens.mypost.MyPostViewModel
import com.oscar.gamermvvmapp.presentation.utils.Vibrate

@Composable
fun MyPostContent(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    post: List<Post>,
    vm: MyPostViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    Log.d("PostListContent", "Wifi ${vm.wife}")

    if (!vm.wife) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(70.dp),
                imageVector = Icons.Default.WifiOff,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "No hay internet disponible", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))
            DefaultButtom(
                text = "Volver a cargar",
                onClick = {
                    Vibrate.vibrar(context)
                    vm.wife = vm.isInternerAvalibleView()
                    Log.d("PostListContent", "Wifi nuevo ${vm.wife}")
                }
            )
        }
    } else if (post.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(70.dp),
                imageVector = Icons.Default.PlaylistRemove,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "No tienes ningun post aun", style = MaterialTheme.typography.titleMedium)
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(paddingValues).padding(bottom = 70.dp)
        ) {
            items(post) {
                CardMyPost(navHostController = navHostController, it)
            }
        }
    }

}