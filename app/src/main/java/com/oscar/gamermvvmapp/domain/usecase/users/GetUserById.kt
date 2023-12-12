package com.oscar.gamermvvmapp.domain.usecase.users

import com.oscar.gamermvvmapp.domain.repository.UsersRepository
import javax.inject.Inject

class GetUserById @Inject constructor(private val usersRepository: UsersRepository) {
    operator fun invoke(id: String) = usersRepository.getUserById(id)

}