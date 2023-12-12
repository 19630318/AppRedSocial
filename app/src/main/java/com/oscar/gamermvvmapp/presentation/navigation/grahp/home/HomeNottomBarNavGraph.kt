package com.oscar.gamermvvmapp.presentation.navigation.grahp.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.oscar.gamermvvmapp.presentation.navigation.Graph
import com.oscar.gamermvvmapp.presentation.navigation.grahp.details.DetailsNavGraph
import com.oscar.gamermvvmapp.presentation.navigation.screen.home.HomeBottomBarScreen
import com.oscar.gamermvvmapp.presentation.screens.mypost.MyPostScreen
import com.oscar.gamermvvmapp.presentation.screens.postlist.PostListScreen
import com.oscar.gamermvvmapp.presentation.screens.profile.ProfileScreen

@Composable
fun HomeNottomBarNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.HOME,
        startDestination = HomeBottomBarScreen.PostList.router
    ){
        DetailsNavGraph(navHostController)
        composable(route = HomeBottomBarScreen.PostList.router){
            PostListScreen(navHostController)
        }
        composable(route = HomeBottomBarScreen.MyPost.router){
            MyPostScreen(navHostController)
        }
        composable(route = HomeBottomBarScreen.Profile.router){
            ProfileScreen(navHostController)
        }
    }

}