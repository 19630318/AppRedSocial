package com.oscar.gamermvvmapp.presentation.navigation.grahp.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.oscar.gamermvvmapp.presentation.navigation.screen.auth.AuthScreen
import com.oscar.gamermvvmapp.presentation.navigation.Graph
import com.oscar.gamermvvmapp.presentation.screens.login.LoginScreen
import com.oscar.gamermvvmapp.presentation.screens.signup.SignupScreen


fun NavGraphBuilder.AuthNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(navHostController)
        }
        composable(route = AuthScreen.Signup.route) {
            SignupScreen(navHostController)
        }
    }
}