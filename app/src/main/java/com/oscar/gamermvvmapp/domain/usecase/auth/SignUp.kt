package com.oscar.gamermvvmapp.domain.usecase.auth

import com.oscar.gamermvvmapp.domain.model.User
import com.oscar.gamermvvmapp.domain.repository.AuthRepository
import javax.inject.Inject

class SignUp @Inject constructor(private val authRepository: AuthRepository) {

    suspend operator fun invoke(user: User) = authRepository.signUp(user)

}