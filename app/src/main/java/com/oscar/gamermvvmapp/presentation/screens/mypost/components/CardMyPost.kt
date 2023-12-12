package com.oscar.gamermvvmapp.presentation.screens.mypost.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.oscar.gamermvvmapp.domain.model.Post
import com.oscar.gamermvvmapp.presentation.components.DefaultCircularProgressIndicator
import com.oscar.gamermvvmapp.presentation.navigation.screen.details.DetailsScreen
import com.oscar.gamermvvmapp.presentation.screens.mypost.MyPostViewModel
import com.oscar.gamermvvmapp.presentation.utils.Vibrate

@Composable
fun CardMyPost(navHostController: NavHostController, post: Post, vm: MyPostViewModel = hiltViewModel()) {

    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth().padding(10.dp).clickable {
            Vibrate.vibrar(context)
            navHostController.navigate(route = DetailsScreen.DetailPost.passPost(post.toJson()))
        },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                model = post.image,
                loading = {
                    DefaultCircularProgressIndicator()
                },
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
            ) {
                Spacer(modifier = Modifier.height(7.dp))
                Text(text = post.name, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        Vibrate.vibrar(context)
                        navHostController.navigate(route = DetailsScreen.UpdatePost.passPost(post.toJson()))
                    }){
                        Icon(
                            modifier = Modifier.size(25.dp),
                            imageVector = Icons.Default.Edit,
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    IconButton(onClick = {
                        Vibrate.vibrar(context)
                        vm.delete(post.id)
                    }){
                        Icon(
                            modifier = Modifier.size(25.dp),
                            imageVector = Icons.Default.Delete,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }

}