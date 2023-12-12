package com.oscar.gamermvvmapp.presentation.screens.detailpost.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.oscar.gamermvvmapp.R
import com.oscar.gamermvvmapp.presentation.components.DefaultCircularProgressIndicator
import com.oscar.gamermvvmapp.presentation.screens.detailpost.DetailPostViewModel
import com.oscar.gamermvvmapp.presentation.utils.Vibrate


@Composable
fun DetailPostContent(navHostController: NavHostController, paddingValues: PaddingValues, vm: DetailPostViewModel = hiltViewModel()) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth().padding(paddingValues).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(300.dp)
        ){
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                model = vm.post.image,
                loading = {
                    DefaultCircularProgressIndicator()
                },
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            IconButton(onClick = {
                navHostController?.popBackStack()
                Vibrate.vibrar(context)
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        if (!vm.post.user.username.isNullOrBlank()) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(top = 15.dp, start = 10.dp, end = 10.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(15.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape),
                        model = vm.post.user.image,
                        loading = {
                            CircularProgressIndicator()
                        },
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = vm.post.user.username, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = vm.post.user.email, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
        Divider(
            modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp), thickness = 2.dp
        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
        ) {
            Text(
                modifier = Modifier,
                text = vm.post.name,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(15.dp))
            Card(
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 7.dp, horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(
                            id = when(vm.post.category){
                                "PC" -> R.drawable.icon_pc
                                "PS4" -> R.drawable.icon_ps4
                                "XBOX" -> R.drawable.icon_xbox
                                "NINTENDO" -> R.drawable.icon_nintendo
                                "MOVIL" -> R.drawable.icon_movil
                                else ->  R.drawable.icon_ps4
                            }
                        ),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(text = vm.post.category, style = MaterialTheme.typography.titleMedium)
                }
            }
        }
        Divider(
            modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp), thickness = 2.dp
        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
        ) {
            Text(
                modifier = Modifier,
                text = "DECRIPCION",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier,
                text = vm.post.description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )
        }
    }

}