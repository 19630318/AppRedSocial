package com.oscar.gamermvvmapp.domain.usecase.auth

import com.oscar.gamermvvmapp.domain.repository.AuthRepository
import javax.inject.Inject

class Login @Inject constructor(private val authRepository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String) = authRepository.login(email, password)

}