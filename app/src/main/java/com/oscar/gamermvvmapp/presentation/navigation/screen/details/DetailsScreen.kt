package com.oscar.gamermvvmapp.presentation.navigation.screen.details

sealed class DetailsScreen(val route: String) {
    object NewPost: DetailsScreen("post/new")
    object ProfileEdit: DetailsScreen("profile/edit/{user}"){
        fun passUser(user: String) = "profile/edit/${user}"
    }
    object DetailPost: DetailsScreen("post/detail/{post}"){
        fun passPost(post: String) = "post/detail/${post}"
    }
    object UpdatePost: DetailsScreen("post/update/{post}"){
        fun passPost(post: String) = "post/update/${post}"
    }
}