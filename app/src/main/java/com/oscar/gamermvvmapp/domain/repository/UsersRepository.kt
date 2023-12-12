package com.oscar.gamermvvmapp.domain.repository

import com.oscar.gamermvvmapp.domain.model.Response
import com.oscar.gamermvvmapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.io.File

interface UsersRepository {

    suspend fun create(user: User): Response<Boolean>

    fun getUserById(id: String): Flow<User>

    suspend fun saveImage(file: File): Response<String>

    suspend fun update(user: User): Response<Boolean>

}