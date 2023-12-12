package com.oscar.gamermvvmapp.domain.usecase.post

import com.oscar.gamermvvmapp.domain.model.Post
import com.oscar.gamermvvmapp.domain.repository.PostRepository
import java.io.File
import javax.inject.Inject

class CreatePost @Inject constructor(private val postRepository: PostRepository) {

    suspend operator fun invoke(post: Post, file: File) = postRepository.create(post, file)

}