package com.example.testtasknetronic.domain.repository

import com.example.testtasknetronic.data.model.UserResponse
import com.example.testtasknetronic.domain.model.UserModel
import retrofit2.Response

interface UserRepository {
    suspend fun getUsers(number: Int): Response<UserResponse>
    suspend fun saveListUsers(data: List<UserModel>)
}