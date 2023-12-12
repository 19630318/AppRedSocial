package com.oscar.gamermvvmapp.presentation.screens.login

import android.content.Context
import android.content.SharedPreferences
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.oscar.gamermvvmapp.domain.model.Response
import com.oscar.gamermvvmapp.domain.usecase.auth.AuthUseCase
import com.oscar.gamermvvmapp.presentation.utils.Vibrate
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val sharedPreferences: SharedPreferences,
    @ApplicationContext context: Context
) : ViewModel() {


    var state by mutableStateOf(LoginState())
        private set

    var isEmailValid by mutableStateOf(false)
    var emailErrorMgs by mutableStateOf("")

    var isPasswordValid by mutableStateOf(false)
    var passwordErrorMgs by mutableStateOf("")

    var isEnabledLoginButtom by mutableStateOf(false)

    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    var saveEmail by mutableStateOf(false)


    init {
        if (authUseCase.getCurrentUser() != null) {
            loginResponse = Response.Success(authUseCase.getCurrentUser()!!)
        }
        if (sharedPreferences.getBoolean("saveEmail", false)){
            saveEmail = true
            state = state.copy(email = sharedPreferences.getString("email", "").toString())
            validEmail()
        }
    }

    //Otra forma de manejar los estados
    /*private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(null)

    val loginFlow: StateFlow<Response<FirebaseUser>?> = _loginFlow*/

    fun login() = viewModelScope.launch {
        loginResponse = Response.Loading
        val result = authUseCase.login(state.email, state.password)
        loginResponse = result
    }

    fun saveEmail() {
        if (saveEmail) {
            sharedPreferences.edit().putBoolean("saveEmail", true).apply()
            sharedPreferences.edit().putString("email", state.email).apply()
        }else{
            sharedPreferences.edit().clear().apply()
        }
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }

    fun enabledLoginButtom() {
        isEnabledLoginButtom = isPasswordValid && isEmailValid
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

}