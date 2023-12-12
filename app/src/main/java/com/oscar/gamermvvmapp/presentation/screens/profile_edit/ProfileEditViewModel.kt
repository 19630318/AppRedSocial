package com.oscar.gamermvvmapp.presentation.screens.profile_edit

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.oscar.gamermvvmapp.domain.model.Response
import com.oscar.gamermvvmapp.domain.model.User
import com.oscar.gamermvvmapp.domain.usecase.users.UsersUseCase
import com.oscar.gamermvvmapp.presentation.screens.signup.SignupState
import com.oscar.gamermvvmapp.presentation.utils.ComposeFileProvider
import com.oscar.gamermvvmapp.presentation.utils.InternetAvailable
import com.oscar.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val usersUseCase: UsersUseCase,
    @ApplicationContext private val context: Context,
    private var resultingActivityHandler: ResultingActivityHandler
): ViewModel() {

    var state by mutableStateOf(ProfileEditState())
        private set

    var isUserNameValid by mutableStateOf(false)
    var userNameErrorMgs by mutableStateOf("")

    val userString = savedStateHandle.get<String>("user")
    val user = User.fromJson(userString!!)

    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    var saveImageResponse by mutableStateOf<Response<String>?>(null)
        private set

    var file: File? = null

    init {
        state =  state.copy(userName = user.username, image =  user.image)
    }

    val resultHandler = resultingActivityHandler

    fun saveImage() = viewModelScope.launch {
        Log.d("ProfileEditViewModel","File: $file")
        if (file != null){
            saveImageResponse = Response.Loading
            val result = usersUseCase.saveImage(file!!)
            saveImageResponse = result
        }
    }

    fun pickImage() = viewModelScope.launch {
        val result = resultHandler.getContent("image/*")
        if (result != null){
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

    fun isInternerAvalibleView(): Boolean = InternetAvailable.isInternetAvailable(context)


    fun onUpdate(url: String){
        val myUser = User(
            id = user.id,
            username = state.userName,
            image = url
        )
        update(myUser)
    }

    fun update(user: User) = viewModelScope.launch {
        updateResponse = Response.Loading
        val result = usersUseCase.update(user)
        updateResponse = result
    }

    fun onUserNameInput(userName: String){
        state = state.copy(userName = userName)
    }

    fun validUserName() {
        if (state.userName.length >= 5) {
            isUserNameValid = true
            userNameErrorMgs = ""
        } else {
            isUserNameValid = false
            userNameErrorMgs = "The user name is invalid"
        }
    }

}