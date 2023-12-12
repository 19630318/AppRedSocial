package com.oscar.gamermvvmapp.domain.usecase.post

import com.oscar.gamermvvmapp.domain.repository.PostRepository
import javax.inject.Inject

class GetPosts @Inject constructor(private val postRepository: PostRepository) {

    operator fun invoke() = postRepository.getPost()

}