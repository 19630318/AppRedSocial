package com.oscar.gamermvvmapp.domain.repository

import com.oscar.gamermvvmapp.domain.model.Post
import com.oscar.gamermvvmapp.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostRepository {

    suspend fun create(post: Post, file: File): Response<Boolean>

    suspend fun update(post: Post, file: File?): Response<Boolean>

    fun getPost(): Flow<Response<List<Post>>>

    fun getPostByUserId(idUser: String): Flow<Response<List<Post>>>

    suspend fun delete(idPost: String): Response<Boolean>

    suspend fun like(idPost: String, idUser: String): Response<Boolean>
    suspend fun deleteLike(idPost: String, idUser: String): Response<Boolean>

}