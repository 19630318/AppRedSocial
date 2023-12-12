package com.oscar.gamermvvmapp.presentation.screens.signup

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.oscar.gamermvvmapp.domain.model.Response
import com.oscar.gamermvvmapp.domain.model.User
import com.oscar.gamermvvmapp.domain.usecase.auth.AuthUseCase
import com.oscar.gamermvvmapp.domain.usecase.users.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val usersUseCase: UsersUseCase
) : ViewModel() {

    var state by mutableStateOf(SignupState())
        private set

    var isUserNameValid by mutableStateOf(false)
    var userNameErrorMgs by mutableStateOf("")

    var isEmailValid by mutableStateOf(false)
    var emailErrorMgs by mutableStateOf("")

    var isPasswordValid by mutableStateOf(false)
    var passwordErrorMgs by mutableStateOf("")

    var isConfirmPasswordValid by mutableStateOf(false)
    var confirmPasswordErrorMgs by mutableStateOf("")

    var isEnabledLoginButtom by mutableStateOf(false)

    var signUpResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    var user = User()

    fun onSignUp() {
        user.username = state.userName
        user.email = state.email
        user.password = state.password
        signUp(user)
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCase.getCurrentUser()!!.uid
        usersUseCase.create(user)
    }

    fun signUp(user: User) = viewModelScope.launch {
        signUpResponse = Response.Loading
        val result = authUseCase.signUp(user)
        signUpResponse = result
    }

    fun onEmailInput(email: String){
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String){
        state = state.copy(password = password)
    }

    fun onUserNameInput(userName: String){
        state = state.copy(userName = userName)
    }

    fun onConfirmPasswordInput(confirmPassword: String){
        state = state.copy(confrimPassword = confirmPassword)
    }


    fun enabledLoginButtom() {
        isEnabledLoginButtom =
            isPasswordValid && isEmailValid && isUserNameValid && isConfirmPasswordValid
    }

    fun validUserName() {
        if (state.userName.length >= 5) {
            isUserNameValid = true
            userNameErrorMgs = ""
        } else {
            isUserNameValid = false
            userNameErrorMgs = "The user name is invalid"
        }
        enabledLoginButtom()
    }

    fun validEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            isEmailValid = true
            emailErrorMgs = ""
        } else {
            isEmailValid = false
            emailErrorMgs = "The email is invalid"
        }
        enabledLoginButtom()
    }

    fun validPassword() {
        if (state.password.length >= 6) {
            isPasswordValid = true
            passwordErrorMgs = ""
        } else {
            isPasswordValid = false
            passwordErrorMgs = "The password is too short"
        }
        enabledLoginButtom()
    }

    fun validConfirmPassword() {
        if (state.password == state.confrimPassword) {
            isConfirmPasswordValid = true
            confirmPasswordErrorMgs = ""
        } else {
            isConfirmPasswordValid = false
            confirmPasswordErrorMgs = "The password is not same"
        }
        enabledLoginButtom()
    }


}