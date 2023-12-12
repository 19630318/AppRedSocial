package com.oscar.gamermvvmapp.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.oscar.gamermvvmapp.presentation.navigation.screen.auth.AuthScreen
import com.oscar.gamermvvmapp.presentation.utils.Vibrate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    title: String,
    upAvailable: Boolean = false,
    navHostController: NavHostController? = null,
    enableActions: Boolean = false

) {

    val context = LocalContext.current

    TopAppBar(
        title = { Text(text = title, style = MaterialTheme.typography.titleLarge) },
        colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary),
        navigationIcon = {
            if (upAvailable) {
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
        },
        actions = {
            if (enableActions){
                IconButton(onClick = { navHostController?.navigate(route = AuthScreen.Signup.route) }) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    )
}