package com.oscar.gamermvvmapp.presentation.navigation.grahp.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.oscar.gamermvvmapp.presentation.navigation.Graph
import com.oscar.gamermvvmapp.presentation.navigation.grahp.auth.AuthNavGraph
import com.oscar.gamermvvmapp.presentation.navigation.screen.root.RootScreen
import com.oscar.gamermvvmapp.presentation.screens.home.HomeScreen

@Composable
fun RootNavGraph(navHostController: NavHostController) {
    NavHost(
        route = Graph.ROOT,
        navController = navHostController,
        startDestination = Graph.AUTHENTICATION
    ) {
        AuthNavGraph(navHostController = navHostController)
        composable(route = Graph.HOME) {
            HomeScreen()
        }
    }
}