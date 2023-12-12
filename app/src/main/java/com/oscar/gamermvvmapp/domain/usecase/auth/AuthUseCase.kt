package com.oscar.gamermvvmapp.domain.usecase.auth

data class AuthUseCase(
    val login: Login,
    val getCurrentUser: GetCurrentUser,
    val logout: Logout,
    val signUp: SignUp
)