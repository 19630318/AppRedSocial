package com.oscar.gamermvvmapp.domain.usecase.auth

import com.oscar.gamermvvmapp.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(private val authRepository: AuthRepository) {

    operator fun invoke() = authRepository.currentUser

}