package com.oscar.gamermvvmapp.presentation.screens.postlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.gamermvvmapp.domain.model.Post
import com.oscar.gamermvvmapp.domain.model.Response
import com.oscar.gamermvvmapp.domain.usecase.auth.AuthUseCase
import com.oscar.gamermvvmapp.domain.usecase.post.PostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val postUseCase: PostUseCase, private val authUseCase: AuthUseCase): ViewModel() {

    var postsResponse by mutableStateOf<Response<List<Post>>?>(null)

    var likeResponse by mutableStateOf<Response<Boolean>?>(null)

    var deleteResponse by mutableStateOf<Response<Boolean>?>(null)

    val userCurrent = authUseCase.getCurrentUser()?.uid!!

    init {
        getPost()
    }

    fun like(idPost: String) = viewModelScope.launch {
        likeResponse = Response.Loading
        val result = postUseCase.likePost(idPost, userCurrent)
        likeResponse = result
    }

    fun deleteLike(idPost: String) = viewModelScope.launch {
        deleteResponse = Response.Loading
        val result = postUseCase.deleteLikePost(idPost, userCurrent)
        deleteResponse = result
    }

    fun getPost() = viewModelScope.launch {
        postsResponse = Response.Loading
        postUseCase.getPosts().collect(){
            postsResponse = it
        }
    }


}