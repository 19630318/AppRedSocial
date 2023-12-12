package com.oscar.gamermvvmapp.domain.usecase.auth

import com.oscar.gamermvvmapp.domain.repository.AuthRepository
import javax.inject.Inject

class Logout @Inject constructor(private val repository: AuthRepository){

    operator fun invoke() = repository.logout()

}