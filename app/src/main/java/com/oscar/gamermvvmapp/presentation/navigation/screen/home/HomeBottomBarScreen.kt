package com.oscar.gamermvvmapp.presentation.navigation.screen.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class HomeBottomBarScreen (
    val router : String,
    var title: String,
    var icon: ImageVector
){
    object PostList: HomeBottomBarScreen(
        router = "post_list",
        title = "Posts",
        icon = Icons.Default.List
    )
    object MyPost: HomeBottomBarScreen(
        router = "my_list",
        title = "Mis Posts",
        icon = Icons.Outlined.List
    )
    object Profile: HomeBottomBarScreen(
        router = "profile",
        title = "Perfil",
        icon = Icons.Default.Person
    )
}