package com.oscar.gamermvvmapp.presentation.screens.updatepost

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.gamermvvmapp.R
import com.oscar.gamermvvmapp.domain.model.Post
import com.oscar.gamermvvmapp.domain.model.Response
import com.oscar.gamermvvmapp.domain.model.User
import com.oscar.gamermvvmapp.domain.usecase.auth.AuthUseCase
import com.oscar.gamermvvmapp.domain.usecase.post.PostUseCase
import com.oscar.gamermvvmapp.presentation.utils.ComposeFileProvider
import com.oscar.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class UpdatePostViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val resultingActivityHandler: ResultingActivityHandler,
    private val postUseCase: PostUseCase,
    private val authUseCase: AuthUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(UpdatePostState())

    var updatePostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    var file: File? = null

    val postString = savedStateHandle.get<String>("post")
    val post = Post.fromJson(postString!!)

    val radioOptions = listOf(
        CategoryRadioButton("PC", R.drawable.icon_pc),
        CategoryRadioButton("PS4", R.drawable.icon_ps4),
        CategoryRadioButton("XBOX", R.drawable.icon_xbox),
        CategoryRadioButton("NITENDO", R.drawable.icon_nintendo),
        CategoryRadioButton("MOBIL", R.drawable.icon_movil)

    )

    val resultHandler = resultingActivityHandler

    init {
        state = state.copy(
            name = post.name,
            description = post.description,
            image = post.image,
            category = post.category
        )
    }

    fun onUpdatePost(){
        val post = Post(
            id = post.id,
            name = state.name,
            description = state.description,
            category = state.category,
            image = post.image,
            idUser = authUseCase.getCurrentUser()?.uid?:""
        )
        updatePost(post)
    }

    fun updatePost(post: Post) = viewModelScope.launch {
        updatePostResponse = Response.Loading
        val result = postUseCase.updatePost(post, file)
        updatePostResponse = result
    }

    fun pickImage() = viewModelScope.launch {
        val result = resultHandler.getContent("image/*")
        if (result != null) {
            file = ComposeFileProvider.createFileFromUri(context, result)
            state = state.copy(image = result.toString())
        }
    }

    fun takePhoto() = viewModelScope.launch {
        val result = resultHandler.takePicturePreview()
        if (result != null) {
            state = state.copy(image = ComposeFileProvider.getPathFromBitmap(context, result))
            file = File(state.image)
        }
    }

    fun onNameInput(name: String) {
        state = state.copy(name = name)
    }

    fun onDescriptionInput(description: String) {
        state = state.copy(description = description)
    }

    fun onCategoryInput(category: String) {
        state = state.copy(category = category)
    }

    fun onImageInput(image: String) {
        state = state.copy(image = image)
    }

}

data class CategoryRadioButton(
    var categoria: String,
    var imagen: Int
)