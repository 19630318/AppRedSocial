package com.oscar.gamermvvmapp.domain.usecase.users

data class UsersUseCase(
    val create: Create,
    val getUserById: GetUserById,
    val update: Update,
    val saveImage: SaveImage
)
