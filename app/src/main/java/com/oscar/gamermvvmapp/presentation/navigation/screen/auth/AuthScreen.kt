package com.oscar.gamermvvmapp.presentation.navigation.screen.auth

sealed class AuthScreen(val route: String){
    object Login: AuthScreen("login")
    object Signup: AuthScreen("signup")
    //object Profile: AuthScreen("profile")
    //object ProfileEdit: AuthScreen("profile/edit/{user}"){
    //    fun passUser(user: String) = "profile/edit/${user}"
    //}
}