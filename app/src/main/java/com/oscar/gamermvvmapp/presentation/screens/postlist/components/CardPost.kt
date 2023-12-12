package com.oscar.gamermvvmapp.presentation.screens.postlist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.oscar.gamermvvmapp.R
import com.oscar.gamermvvmapp.domain.model.Post
import com.oscar.gamermvvmapp.presentation.components.DefaultCircularProgressIndicator
import com.oscar.gamermvvmapp.presentation.navigation.screen.details.DetailsScreen
import com.oscar.gamermvvmapp.presentation.screens.postlist.PostViewModel
import com.oscar.gamermvvmapp.presentation.utils.Vibrate

@Composable
fun CardPost(navHostController: NavHostController, post: Post, vm: PostViewModel = hiltViewModel()) {

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
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = post.name, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.width(250.dp),
                        text = "Publicado por: ${post.user.username}",
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (post.likes.contains(vm.userCurrent)) {
                        IconButton(onClick = {
                            vm.deleteLike(post.id)
                        }) {
                            Icon(
                                modifier = Modifier.size(25.dp),
                                painter = painterResource(R.drawable.icon_favorite),
                                contentDescription = null
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            vm.like(post.id)
                        }) {
                            Icon(
                                modifier = Modifier.size(25.dp),
                                painter = painterResource(R.drawable.icon_favorite_outlined),
                                contentDescription = null
                            )
                        }
                    }
                    Text(text = post.likes.size.toString(), style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }

}