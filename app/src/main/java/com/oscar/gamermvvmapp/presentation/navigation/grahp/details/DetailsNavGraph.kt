package com.oscar.gamermvvmapp.presentation.navigation.grahp.details

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.oscar.gamermvvmapp.presentation.navigation.Graph
import com.oscar.gamermvvmapp.presentation.navigation.screen.auth.AuthScreen
import com.oscar.gamermvvmapp.presentation.navigation.screen.details.DetailsScreen
import com.oscar.gamermvvmapp.presentation.screens.detailpost.DetailPostScreen
import com.oscar.gamermvvmapp.presentation.screens.login.LoginScreen
import com.oscar.gamermvvmapp.presentation.screens.newpost.NewPostScreen
import com.oscar.gamermvvmapp.presentation.screens.profile_edit.ProfileEditScreen
import com.oscar.gamermvvmapp.presentation.screens.signup.SignupScreen
import com.oscar.gamermvvmapp.presentation.screens.updatepost.UpdatePostScreen

fun NavGraphBuilder.DetailsNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.ProfileEdit.route
    ){
        composable(route= DetailsScreen.NewPost.route){
            NewPostScreen(navHostController)
        }

        composable(route = DetailsScreen.ProfileEdit.route
            , arguments = listOf(navArgument("user"){
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("user")?.let {
                ProfileEditScreen(navHostController, it)
            }
        }
        composable(route = DetailsScreen.DetailPost.route
            , arguments = listOf(navArgument("post"){
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("post")?.let {
                DetailPostScreen(navHostController, it)
            }
        }
        composable(route = DetailsScreen.UpdatePost.route
            , arguments = listOf(navArgument("post"){
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("post")?.let {
                UpdatePostScreen(navHostController, it)
            }
        }
    }
}