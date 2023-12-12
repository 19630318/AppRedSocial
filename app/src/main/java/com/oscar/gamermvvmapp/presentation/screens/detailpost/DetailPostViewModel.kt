package com.oscar.gamermvvmapp.presentation.screens.detailpost

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.oscar.gamermvvmapp.domain.model.Post
import com.oscar.gamermvvmapp.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val postString = savedStateHandle.get<String>("post")
    val post = Post.fromJson(postString!!)

}