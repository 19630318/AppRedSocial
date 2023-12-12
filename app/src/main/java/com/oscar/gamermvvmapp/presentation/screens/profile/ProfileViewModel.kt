package com.oscar.gamermvvmapp.presentation.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.gamermvvmapp.domain.model.User
import com.oscar.gamermvvmapp.domain.usecase.auth.AuthUseCase
import com.oscar.gamermvvmapp.domain.usecase.users.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val usersUseCase: UsersUseCase
) : ViewModel() {

    var ocultar  by mutableStateOf(true)

    var userData by mutableStateOf(User())
        private set

    init {
        getUserById()
    }

    private fun getUserById() = viewModelScope.launch {
        usersUseCase.getUserById(authUseCase.getCurrentUser()!!.uid).collect(){
            userData = it
        }
    }

    fun logOut() {
        authUseCase.logout()
    }

}