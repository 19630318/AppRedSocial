package com.oscar.gamermvvmapp.presentation.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.oscar.gamermvvmapp.presentation.navigation.grahp.home.HomeNottomBarNavGraph
import com.oscar.gamermvvmapp.presentation.navigation.screen.home.HomeBottomBarScreen
import com.oscar.gamermvvmapp.presentation.screens.profile.ProfileViewModel
import com.oscar.gamermvvmapp.presentation.utils.Vibrate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController = rememberNavController()) {

    Scaffold(
        bottomBar = {
                BottomBar(navHostController)
        }
    ){
        HomeNottomBarNavGraph(navHostController)
    }


}

@Composable
fun BottomBar(navHostController: NavHostController){
    val screens = listOf(
        HomeBottomBarScreen.PostList,
        HomeBottomBarScreen.MyPost,
        HomeBottomBarScreen.Profile
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any{ it.router == currentDestination?.route}

    if(bottomBarDestination){
        NavigationBar {
            screens.forEach {
                AddItem(
                    screen = it,
                    currentDestination = currentDestination,
                    navHostController = navHostController
                )
            }
        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: HomeBottomBarScreen,
    currentDestination: NavDestination?,
    navHostController: NavHostController
){
    val context = LocalContext.current

    NavigationBarItem(
        label = { Text(text = screen.title) },
        icon = { Icon(imageVector = screen.icon, contentDescription = null) },
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.router
        } == true,
        onClick = {
            navHostController.navigate(screen.router){
                popUpTo(navHostController.graph.findStartDestination().id)
                launchSingleTop = true
            }
            Vibrate.vibrar(context)
        }
    )
}