package com.oscar.gamermvvmapp.presentation.screens.profile.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.oscar.gamermvvmapp.R
import com.oscar.gamermvvmapp.presentation.screens.profile.ProfileViewModel
import com.oscar.gamermvvmapp.presentation.components.DefaultButtom
import com.oscar.gamermvvmapp.presentation.MainActivity
import com.oscar.gamermvvmapp.presentation.navigation.screen.auth.AuthScreen
import com.oscar.gamermvvmapp.presentation.navigation.screen.details.DetailsScreen

@Composable
fun ProfileContent(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    vm: ProfileViewModel = hiltViewModel()
) {

    val activity = LocalContext.current as? Activity

    Column(
        modifier = Modifier.fillMaxSize().padding(paddingValues)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                modifier = Modifier.fillMaxWidth().height(200.dp),
                painter = painterResource(R.drawable.background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.6f
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Welcome",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(55.dp))
                if (vm.userData.image == "") {
                    Image(
                        modifier = Modifier.size(115.dp),
                        painter = painterResource(R.drawable.user),
                        contentDescription = null
                    )
                } else {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .size(115.dp)
                            .clip(CircleShape),
                        model = vm.userData.image,
                        loading = {
                            CircularProgressIndicator()
                        },
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(55.dp))
            Text(
                text = vm.userData.username,
                style = MaterialTheme.typography.titleMedium,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = vm.userData.email, style = MaterialTheme.typography.bodyLarge, fontStyle = FontStyle.Italic)
            Spacer(modifier = Modifier.height(20.dp))
            DefaultButtom(
                modifier = Modifier.width(200.dp).padding(horizontal = 10.dp),
                icon = Icons.Default.Edit,
                text = "Editar datos",
                onClick = {
                    navHostController.navigate(
                        route = DetailsScreen.ProfileEdit.passUser(
                            vm.userData.toJson()
                        )
                    )
                },
                colorButtom = MaterialTheme.colorScheme.onBackground,
                colorText = MaterialTheme.colorScheme.background
            )
            Spacer(modifier = Modifier.height(15.dp))
            DefaultButtom(
                modifier = Modifier.width(200.dp).padding(horizontal = 10.dp),
                text = "Cerrar sesion",
                icon = Icons.Default.Close,
                onClick = {
                    vm.logOut()
                    activity?.finish()
                    activity?.startActivity(Intent(activity, MainActivity::class.java))
                }
            )
        }
    }
}