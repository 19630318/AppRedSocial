package com.oscar.gamermvvmapp.presentation.navigation.screen.root

sealed class RootScreen(val route: String){
    object Home: RootScreen("home")
}