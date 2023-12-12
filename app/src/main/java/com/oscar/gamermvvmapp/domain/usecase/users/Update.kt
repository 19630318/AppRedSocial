package com.oscar.gamermvvmapp.domain.usecase.users

import com.oscar.gamermvvmapp.domain.model.User
import com.oscar.gamermvvmapp.domain.repository.UsersRepository
import javax.inject.Inject

class Update @Inject constructor(private val usersRepository: UsersRepository) {

    suspend operator fun invoke(user: User) = usersRepository.update(user)

}