package com.oscar.gamermvvmapp.domain.usecase.post

data class PostUseCase(
    val createPost: CreatePost,
    val getPosts: GetPosts,
    val getPostsByUserId: GetPostsByUserId,
    val deletePost: DeletePost,
    val updatePost: UpdatePost,
    val likePost: LikePost,
    val deleteLikePost: DeleteLikePost
)
