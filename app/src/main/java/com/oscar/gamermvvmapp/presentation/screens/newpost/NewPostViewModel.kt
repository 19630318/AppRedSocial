package com.oscar.gamermvvmapp.presentation.screens.newpost

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.gamermvvmapp.R
import com.oscar.gamermvvmapp.domain.model.Post
import com.oscar.gamermvvmapp.domain.model.Response
import com.oscar.gamermvvmapp.domain.usecase.auth.AuthUseCase
import com.oscar.gamermvvmapp.domain.usecase.post.PostUseCase
import com.oscar.gamermvvmapp.presentation.utils.ComposeFileProvider
import com.oscar.gamermvvmapp.presentation.utils.InternetAvailable
import com.oscar.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NewPostViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val resultingActivityHandler: ResultingActivityHandler,
    private val postUseCase: PostUseCase,
    private val authUseCase: AuthUseCase
) : ViewModel() {

    var state by mutableStateOf(NewPostState())

    var createPostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    var wife by mutableStateOf(isInternerAvalibleView())

    var file: File? = null

    val radioOptions = listOf(
        CategoryRadioButton("PC", R.drawable.icon_pc),
        CategoryRadioButton("PS4", R.drawable.icon_ps4),
        CategoryRadioButton("XBOX", R.drawable.icon_xbox),
        CategoryRadioButton("NITENDO", R.drawable.icon_nintendo),
        CategoryRadioButton("MOBIL", R.drawable.icon_movil)

    )

    val resultHandler = resultingActivityHandler

    fun isInternerAvalibleView(): Boolean = InternetAvailable.isInternetAvailable(context)

    fun onNewPost(){
        val post = Post(
            name = state.name,
            description = state.description,
            category = state.category,
            idUser = authUseCase.getCurrentUser()?.uid?:""
        )
        createPost(post)
    }

    fun clearForm(){
        state = state.copy(
            name = "",
            description = "",
            category = "",
            image = ""
        )
        createPostResponse = null
    }

    fun createPost(post: Post) = viewModelScope.launch {
        createPostResponse = Response.Loading
        val result = postUseCase.createPost(post, file!!)
        createPostResponse = result
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