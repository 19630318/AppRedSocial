package com.oscar.gamermvvmapp.presentation.screens.mypost

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.gamermvvmapp.domain.model.Post
import com.oscar.gamermvvmapp.domain.model.Response
import com.oscar.gamermvvmapp.domain.usecase.auth.AuthUseCase
import com.oscar.gamermvvmapp.domain.usecase.post.PostUseCase
import com.oscar.gamermvvmapp.presentation.utils.InternetAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPostViewModel @Inject constructor(
    private val postUseCase: PostUseCase,
    private val authUseCase: AuthUseCase,
    @ApplicationContext private val context: Context
): ViewModel() {

    var postsUserResponse by mutableStateOf<Response<List<Post>>?>(null)

    var deletePostResponse by mutableStateOf<Response<Boolean>?>(null)

    var wife by mutableStateOf(isInternerAvalibleView())

    init {
        getPostByUser()
    }

    fun isInternerAvalibleView(): Boolean = InternetAvailable.isInternetAvailable(context)

    fun delete(idPost: String) = viewModelScope.launch {
        deletePostResponse = Response.Loading
        val result = postUseCase.deletePost(idPost)
        deletePostResponse = result
    }

    fun getPostByUser() = viewModelScope.launch {
        postsUserResponse = Response.Loading
        postUseCase.getPostsByUserId(authUseCase.getCurrentUser()?.uid!!).collect(){
            postsUserResponse = it
        }
    }

}